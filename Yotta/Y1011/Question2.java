package Y1011;// 求连续最长质数子数组（即数组中任意两个数都没有除了1以外的因数）
// 等价于：求最长的连续子数组，使其中任意两个数的最大公约数都为 1（两两互质）
//
// 思路：双指针滑动窗口 + 质因数分解
//   两个数不互质 <=> 它们有公共质因子（0 是特例，不能只看质因子，需要单独统计）
//   所以窗口内每个质因子的出现次数都必须 <= 1，否则窗口里就存在一对不互质的数
//   合法性对子集有遗传性（合法窗口的子窗口一定合法），所以双指针成立：
//   右指针右扩，一旦窗口非法就左移左指针，直到窗口重新合法
//
// 复杂度：O(n * ω)，ω 为单个数的不同质因子个数（int 范围内 <= 9），每个元素进出窗口各一次
// 假设：n <= 2e5，|a_i| <= 1e9（质数表筛到 1e5，足够分解 |a_i| <= 1e10 的数）
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Question2 {

    private static final int SIEVE_LIMIT = 100000;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        if (!in.hasNextInt()) {
            return;
        }
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        System.out.println(longestCoprimeSubarray(a));
    }

    /** 返回最长的“任意两个数都互质”的连续子数组长度 */
    public static int longestCoprimeSubarray(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length;
        int[] primes = sieve(SIEVE_LIMIT);

        // 先预处理每个数的不同质因子，避免元素进出窗口时重复分解
        int[][] factors = new int[n][];
        for (int i = 0; i < n; i++) {
            factors[i] = distinctPrimeFactors(Math.abs(a[i]), primes);
        }

        Map<Integer, Integer> primeCount = new HashMap<>(); // 质因子 -> 窗口内含该质因子的元素个数
        int duplicated = 0; // 窗口内出现次数 >= 2 的质因子个数，> 0 说明存在一对不互质的数
        int zeros = 0;      // 窗口内 0 的个数
        int nonUnits = 0;   // 窗口内 |x| > 1 的元素个数

        int answer = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            for (int p : factors[right]) {
                if (primeCount.merge(p, 1, Integer::sum) == 2) {
                    duplicated++;
                }
            }
            if (a[right] == 0) {
                zeros++;
            }
            if (Math.abs(a[right]) > 1) {
                nonUnits++;
            }

            // 非法情况：有公共质因子；或者 0 和 |x|>1 的数同处一个窗口；
            //          或者窗口里有多个 0（gcd(0,0) = 0 != 1）
            while (duplicated > 0 || zeros > 1 || (zeros == 1 && nonUnits > 0)) {
                for (int p : factors[left]) {
                    int c = primeCount.merge(p, -1, Integer::sum);
                    if (c == 1) {
                        duplicated--;
                    }
                    if (c == 0) {
                        primeCount.remove(p);
                    }
                }
                if (a[left] == 0) {
                    zeros--;
                }
                if (Math.abs(a[left]) > 1) {
                    nonUnits--;
                }
                left++;
            }

            answer = Math.max(answer, right - left + 1);
        }
        return answer;
    }

    /** 埃氏筛，返回 [2, limit] 内的所有质数 */
    private static int[] sieve(int limit) {
        boolean[] composite = new boolean[limit + 1];
        int count = 0;
        for (int i = 2; i <= limit; i++) {
            if (!composite[i]) {
                count++;
                for (long j = (long) i * i; j <= limit; j += i) {
                    composite[(int) j] = true;
                }
            }
        }
        int[] primes = new int[count];
        int index = 0;
        for (int i = 2; i <= limit; i++) {
            if (!composite[i]) {
                primes[index++] = i;
            }
        }
        return primes;
    }

    /** 求 v 的所有不同质因子，v <= 1 时返回空数组 */
    private static int[] distinctPrimeFactors(int v, int[] primes) {
        if (v <= 1) {
            return new int[0];
        }
        if (isPrime(v)) { // 大质数直接短路，省掉一整轮试除（都是大质数的数据是很常见的极端情况）
            return new int[]{v};
        }
        int[] buffer = new int[8];
        int size = 0;
        int rest = v;
        for (int p : primes) {
            if ((long) p * p > rest) {
                break;
            }
            if (rest % p == 0) {
                if (size == buffer.length) {
                    buffer = Arrays.copyOf(buffer, size * 2);
                }
                buffer[size++] = p;
                while (rest % p == 0) {
                    rest /= p;
                }
            }
        }
        if (rest > 1) { // 剩下的部分一定是质数
            if (size == buffer.length) {
                buffer = Arrays.copyOf(buffer, size * 2);
            }
            buffer[size++] = rest;
        }
        return Arrays.copyOf(buffer, size);
    }

    /** Miller-Rabin 判质数，底数 2,3,5,7 足以覆盖 32 位整数 */
    private static boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        int[] small = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
        for (int p : small) {
            if (n % p == 0) {
                return n == p;
            }
        }
        long d = n - 1;
        int s = 0;
        while ((d & 1) == 0) {
            d >>= 1;
            s++;
        }
        int[] bases = {2, 3, 5, 7};
        for (int a : bases) {
            long x = powMod(a, d, n);
            if (x == 1 || x == n - 1) {
                continue;
            }
            boolean composite = true;
            for (int i = 1; i < s; i++) {
                x = x * x % n;
                if (x == n - 1) {
                    composite = false;
                    break;
                }
            }
            if (composite) {
                return false;
            }
        }
        return true;
    }

    private static long powMod(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % mod;
            }
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }
}
