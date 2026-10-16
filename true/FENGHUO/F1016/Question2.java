package F1016;

import java.math.BigInteger;
import java.util.*;

/**
 * 小红的表达式
 * 给定一个仅有数字1到9组成的字符串s（s长度小于等于15），可以在字符串s中任意两个相邻的数字字符之间插入'+'，每对相邻数字可选择插入一个
 * 加号或者不插入
 * 通过不同插入方式可以构成多种不同的数学表达式。例如对于"1234"可以有 1234 1+234 12+34 等
 * 但是像 1+2++3+4 这种方式是不合理的
 * 对于给定的字符串s，请计算所有通过合法插入'+'构成的数学表达式中，有多少个表达式的计算结果是一个质数
 *
 * 【输入描述】
 * 一行一个字符串 s，仅由字符 '1' ~ '9' 组成，长度 n <= 15。
 *
 * 【输出描述】
 * 输出一个整数，表示计算结果为质数的表达式个数。
 *
 * 【样例】
 * 输入：
 *   12
 * 输出：
 *   1
 * 说明：
 *   两种表达式：12 = 12（非质数）、1+2 = 3（质数），故答案为 1。
 *
 * 输入：
 *   112
 * 输出：
 *   2
 * 说明：
 *   表达式 1+12 与 11+2 的值都是 13（质数），按表达式个数计，答案为 2。
 *
 * 【说明】
 *   n 个字符之间有 n-1 个相邻间隙，每个间隙可选择"插入 '+' "或"不插入"，共 2^(n-1) 种表达式；
 *   不同的表达式分别计数，即使值相同也各算一个。1 不是质数。
 */
public class Question2 {
    /** Miller-Rabin 底数：对 long 范围内的整数，这组底数足以给出确定性判断 */
    private static final long[] BASES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int n = s.length();

        int count = 0;
        int total = 1 << (n - 1);            // n-1 个间隙，每个有"插 + / 不插"两种选择
        for (int mask = 0; mask < total; mask++) {
            long sum = 0;                    // 该表达式各段之和
            long cur = 0;                    // 当前正在拼接的数字段
            for (int i = 0; i < n; i++) {
                cur = cur * 10 + (s.charAt(i) - '0');
                // 已是最后一个字符，或第 i 个间隙插了 '+'，则当前段结束
                if (i == n - 1 || (mask & (1 << i)) != 0) {
                    sum += cur;
                    cur = 0;
                }
            }
            if (isPrime(sum)) {
                count++;
            }
        }
        System.out.println(count);
    }

    /** 判断 x 是否为质数（Miller-Rabin，对 long 范围内的整数是确定性的） */
    static boolean isPrime(long x) {
        if (x < 2) return false;
        // 先除掉小质数因子；同时保证后面 x 与所有底数互质（排除 x 恰为底数的情况）
        for (long b : BASES) {
            if (x % b == 0) return x == b;
        }
        long d = x - 1;
        int r = 0;
        while ((d & 1) == 0) {               // 把 x-1 写成 d * 2^r
            d >>= 1;
            r++;
        }
        for (long a : BASES) {
            if (!passesRound(a, d, r, x)) {
                return false;                // 找到见证数，x 必为合数
            }
        }
        return true;
    }

    /** 对底数 a 做一轮 Miller-Rabin 检验，返回 false 表示 x 被判定为合数 */
    private static boolean passesRound(long a, long d, int r, long x) {
        long v = powMod(a, d, x);
        if (v == 1 || v == x - 1) return true;
        for (int i = 1; i < r; i++) {
            v = mulMod(v, v, x);
            if (v == x - 1) return true;
        }
        return false;
    }

    /** 快速幂：base^exp mod mod */
    private static long powMod(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = mulMod(res, base, mod);
            base = mulMod(base, base, mod);
            exp >>= 1;
        }
        return res;
    }

    /** 模乘：a*b mod m。a、b < m ≤ 10^15，乘积会超出 long，借 BigInteger 取中间结果 */
    private static long mulMod(long a, long b, long m) {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b))
                .mod(BigInteger.valueOf(m)).longValue();
    }
}