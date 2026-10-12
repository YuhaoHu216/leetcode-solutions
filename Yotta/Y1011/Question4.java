package Y1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 细菌等真核细胞中存在一种环状DNA，假定我们已经知道某一种细菌的环状DNA的碱基序列，现在要判断人类已经探明的基因库中的基因片段是否能够组合出这一种环状基因。
// 第一行输入一个数字N，表示接下来一共要输入N组用例
// 第二到第N+1行每行是一组用例
// 每组用例先输入细菌的环状DNA碱基序列，序列长度不超过100000，输入一个空格，再输入一个数字M（M<10000），M表示接下来要输入基因片段的数量，
// 再输入一个空格，再输入人类已经探明的基因库中全部基因片段的序列（基因片段长度不超过100，之间也用空格输出.
// 每组样例如果能用已知的DNA片段组合出环状DNA输出true，否则flase，N组用例之间结果用空格隔开。所以已探明的基因片段和细菌的环状DNA都是由ACGT四种碱基组合形成，并且基因库里的基因片段没有重复的。
// 示例1：
// 3
// AAAGTCTGACCCCTGGAAGGGGCAAAG 4 AGGGGC AAAG CCTGGA TCTGACC
// AAAAAGTCTAATGCGGGC 5 AGGGGC AAAG CCTGGA TCTGACC TCCCCCCG
// TGGAAGGGGCAAAGCC 4 AGGGGC AAAG CCTGGA TCTGACC
//
// 思路：
//   环可以从任意位置切开，且片段可以重复使用（示例1里 AAAG 就用了两次），
//   所以问题等价于：S 的某个循环移位能否被基因片段线性拼接而成（恰好覆盖，即经典「单词拆分」）。
//
//   片段长度 <= maxLen（<= 100），任何一套拼接方案里相邻切点的间距就是某段的长度，
//   因此沿着环走最多 maxLen 步一定会撞上下一个切点 —— 方案的切点集合必然和 [0, maxLen) 有交集。
//   所以只需枚举起点 start ∈ [0, min(n, maxLen))（最多 100 个），对循环移位 T[start .. start+n) 做一次单词拆分 DP。
//
//   子串是否属于基因库用字典树判定：T = S + S，对每个位置 p 从 trie 根往下走最多 maxLen 步，
//   把命中的片段长度记成位掩码 mask[p]（位 len = 从 p 开始存在长度为 len 的片段）。
//   然后对每个候选起点做一次「恰好覆盖 n 个字符」的反向 DP，见 canSplit。
//
// 复杂度：建 trie O(Σ|piece|)；打掩码 O(n * maxLen)；
//        每个候选起点的 DP 上界是 O(n * maxLen)，但配合「两侧取小」的剪枝实际远小于它
public class Question4 {

    private static StringTokenizer tokens;
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        String first = next();
        if (first == null) {
            return;
        }
        int testCases = Integer.parseInt(first);
        StringBuilder out = new StringBuilder();
        while (testCases-- > 0) {
            String dna = next();
            int m = Integer.parseInt(next());
            String[] pieces = new String[m];
            for (int i = 0; i < m; i++) {
                pieces[i] = next();
            }
            if (out.length() > 0) {
                out.append(' '); // 题目要求：N 组用例之间结果用空格隔开
            }
            out.append(canAssemble(dna, pieces) ? "true" : "false");
        }
        System.out.println(out);
    }

    /** 判断基因库里的片段能否（可重复使用）恰好拼出环状 DNA */
    public static boolean canAssemble(String dna, String[] pieces) {
        int n = dna.length();
        if (n == 0 || pieces == null || pieces.length == 0) {
            return false;
        }

        // 0. 剪枝：环上出现了某个碱基，而整个基因库里都没有这个碱基 —— 必然覆盖不了
        int inDna = 0;
        for (int i = 0; i < n; i++) {
            inDna |= 1 << code(dna.charAt(i));
        }
        int inLibrary = 0;
        for (String p : pieces) {
            if (p == null) {
                continue;
            }
            for (int i = 0; i < p.length(); i++) {
                inLibrary |= 1 << code(p.charAt(i));
            }
        }
        if ((inDna & ~inLibrary) != 0) {
            return false;
        }

        // 1. 过滤掉长度超过整条 DNA 的片段：它们不可能出现在「恰好覆盖 n 个字符」的方案里
        int capacity = 1; // 字典树的根
        int maxLen = 0;
        for (String p : pieces) {
            if (p != null && p.length() > 0 && p.length() <= n) {
                capacity += p.length();
                maxLen = Math.max(maxLen, p.length());
            }
        }
        if (maxLen == 0) {
            return false;
        }

        // 2. 把有效片段插入字典树（片段互不相同，所以一个节点最多对应一个片段）
        int[] child = new int[4 * capacity];
        Arrays.fill(child, -1);
        int[] terminalLen = new int[capacity]; // 0 表示该节点不是某个片段的结尾
        int nodes = 1;
        for (String p : pieces) {
            if (p == null || p.length() == 0 || p.length() > n) {
                continue;
            }
            int node = 0;
            for (int i = 0; i < p.length(); i++) {
                int c = code(p.charAt(i));
                int next = child[node * 4 + c];
                if (next == -1) {
                    next = nodes++;
                    child[node * 4 + c] = next;
                }
                node = next;
            }
            terminalLen[node] = p.length();
        }

        // 3. 在 T = S + S 上，对每个位置 p 记录「从 p 起始的所有片段长度」的位掩码
        String t = dna + dna;
        int total = 2 * n;
        long[] maskLo = new long[total + 1]; // 位 len (1..63)
        long[] maskHi = new long[total + 1]; // 位 len-64 (64..127)
        for (int p = 0; p < total; p++) {
            int node = 0;
            int limit = Math.min(maxLen, total - p);
            for (int len = 1; len <= limit; len++) {
                int next = child[node * 4 + code(t.charAt(p + len - 1))];
                if (next == -1) {
                    break; // 这个方向已经不在字典树里了
                }
                node = next;
                int hit = terminalLen[node];
                if (hit != 0) {
                    if (hit < 64) {
                        maskLo[p] |= 1L << hit;
                    } else {
                        maskHi[p] |= 1L << (hit - 64);
                    }
                }
            }
        }

        // 4. 枚举起点：切点集合必落在 [0, maxLen) 内，所以起点只需试这么多个
        int startRange = Math.min(n, maxLen);
        for (int start = 0; start < startRange; start++) {
            if (maskLo[start] == 0 && maskHi[start] == 0) {
                continue; // 这个位置开不了头
            }
            if (canSplit(maskLo, maskHi, start, n, maxLen)) {
                return true;
            }
        }
        return false;
    }

    // DP 数组跨用例复用，省掉反复分配
    private static boolean[] fin = new boolean[1];
    private static int[] window = new int[2];

    /**
     * 从在环上位置 start 处切开，循环移位 T[start .. start+n) 能否被恰好拆成若干片段。
     *
     * 反向 DP：fin[off] = 「已经覆盖到相对偏移 off，还能恰好走到偏移 n」是否可能，fin[n] = true。
     * 转移 fin[off] = ∃ len ∈ mask[start+off] 且 fin[off+len]。
     *
     * 直接枚举 mask 里的每个 len 是 O(100)/位置；但只有 [off+1, off+maxLen] 这个窗口里的
     * fin[...] 才可能为 true，而窗口里「为 true 的位置」往往非常稀疏，所以用一个双端队列
     * 维护窗口内所有 true 的位置，哪个方向元素少就从哪个方向枚举。
     * 失败的数据（窗口内几乎没有 true）这一步就是 O(1)，成功的数据则很快返回。
     */
    private static boolean canSplit(long[] maskLo, long[] maskHi, int start, int n, int maxLen) {
        if (fin.length < n + 1) {
            fin = new boolean[n + 1];
        }
        if (window.length < maxLen + 2) {
            window = new int[maxLen + 2];
        }
        Arrays.fill(fin, 0, n + 1, false);
        fin[n] = true;

        // 双端队列 window[head .. head+size) 保存窗口 [off+1, min(n, off+maxLen)] 内所有 fin 为 true 的位置，
        // 按位置从小到大排列；队列放在环形数组里，从左边压入 off、从右边弹出 off+maxLen
        int cap = window.length;
        int head = 0;
        int size = 1;
        window[0] = n;

        for (int off = n - 1; off >= 0; off--) {
            int idx = start + off;
            long lo = maskLo[idx];
            long hi = maskHi[idx];
            int candidateCount = Long.bitCount(lo) + Long.bitCount(hi);
            boolean ok = false;
            if (candidateCount > 0) {
                if (size < candidateCount) {
                    // 从「能到达终点的位置」这一侧枚举：len = j - off 必须是 mask 里的长度
                    for (int k = 0; k < size && !ok; k++) {
                        int len = window[(head + k) % cap] - off;
                        ok = len < 64 ? ((lo >>> len) & 1L) != 0 : ((hi >>> (len - 64)) & 1L) != 0;
                    }
                } else {
                    // 从「候选片段长度」这一侧枚举
                    long bits = lo;
                    while (bits != 0) {
                        int len = Long.numberOfTrailingZeros(bits);
                        bits &= bits - 1;
                        if (off + len <= n && fin[off + len]) {
                            ok = true;
                            break;
                        }
                    }
                    bits = hi;
                    while (!ok && bits != 0) {
                        int len = 64 + Long.numberOfTrailingZeros(bits);
                        bits &= bits - 1;
                        if (off + len <= n && fin[off + len]) {
                            ok = true;
                            break;
                        }
                    }
                }
            }
            fin[off] = ok;

            // 窗口左移一位：[off+1, off+maxLen] -> [off, off-1+maxLen]
            if (ok) {
                head = (head - 1 + cap) % cap;
                window[head] = off;
                size++;
            }
            if (off + maxLen <= n && fin[off + maxLen]) {
                size--;
            }
        }
        return fin[0];
    }

    /** A/C/G/T -> 0/1/2/3 */
    private static int code(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            default:
                return 3; // 'T'
        }
    }

    /** 按空白符逐个取词，兼容一组用例被折行书写的情况 */
    private static String next() throws IOException {
        while (tokens == null || !tokens.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) {
                return null;
            }
            tokens = new StringTokenizer(line);
        }
        return tokens.nextToken();
    }
}