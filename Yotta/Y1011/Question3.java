package Y1011;

import java.util.Arrays;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 现在给定一系列方块坐标，依次搭建，要求判定这个搭建序列是否合法
// 要求初始状态只有地面，即z=0；所有方块必须由至少一个面与地面或者其他方块邻接；方块搭建时不行从外部可达（意思是说这个方块一定能从无穷远处通过三方向轴的移动进入搭建位置，也就是说封闭空间无法搭建）；为了方便计算，x，y，z皆为位于[0,100]区间的整数，N<100000
// 输入描述：第一行为一个正整数表示测试样例数量，对于每一个样例，第一行表示正整数M表明该序列有多少方块，接下来M行每行为一个方块的坐标X Y Z，按空格分割；对于每个样例输出YES或NO
//
// 每一块放下时要同时满足三条：
//   1) 该格子上还没有方块（不能重复放置）；
//   2) 有至少一个面与地面(z=0)或已放好的方块相邻（不能悬空）；
//   3) 该格子必须与外界（无穷远）连通，即没有被已放的方块围成封闭空间。
//
// 难点在 3)：正着做的话，每放一块都要重新从外界 BFS 一次，最坏 O(M * 网格) = 1e11，会超时。
// 倒着做：从「只剩下前 m-1 块」的局面出发，依次把第 m、m-1、...、1 块从网格里拿走。
// 拿走方块只会让空格子变多，连通性只增不减，于是可以用并查集维护空格子的连通块，
// 并把「外界」作为一个虚拟节点（区域边界上的空格子都位于方块包围盒之外，那里的空气是连通的，
// 直接和外界合并即可）。拿掉一块时只要把它和 6 个方向的空格子合并，均摊 O(α)。
// 于是判定第 i 块时，第 1..i-1 块正好还在网格里，直接查它和外界是否同属一个集合即可。
//
// 复杂度：O(区域大小 * α + M * α)，区域大小 = 方块包围盒向外扩一格，最大 103*103*102 ≈ 1.1e6
public class Question3 {

    private static final int[] DX = {1, -1, 0, 0, 0, 0};
    private static final int[] DY = {0, 0, 1, -1, 0, 0};
    private static final int[] DZ = {0, 0, 0, 0, 1, -1};

    // 当前用例的网格信息
    private static int xLo, xHi, yLo, yHi, zLo, zHi;
    private static int ny, nz;
    private static int outside;   // 「外界」虚拟节点的下标
    private static int[] parent;  // 并查集：-1 表示该格子被方块占着，不在集合里
    private static int[] count;   // 每个格子上的方块个数
    private static boolean bottomIsOutside; // 区域最下面一层是否在方块包围盒之外（下方没有方块）

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        if (!in.hasNextInt()) {
            return;
        }
        int testCases = in.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testCases-- > 0) {
            int m = in.nextInt();
            int[] xs = new int[m];
            int[] ys = new int[m];
            int[] zs = new int[m];
            for (int i = 0; i < m; i++) {
                xs[i] = in.nextInt();
                ys[i] = in.nextInt();
                zs[i] = in.nextInt();
            }
            sb.append(isValid(xs, ys, zs) ? "YES" : "NO").append('\n');
        }
        System.out.print(sb);
    }

    /** 判断整个搭建序列是否合法 */
    public static boolean isValid(int[] xs, int[] ys, int[] zs) {
        int m = xs.length;
        if (m == 0) {
            return true;
        }

        // 1. 计算区域：所有方块的包围盒向外扩一格
        xLo = xHi = xs[0];
        yLo = yHi = ys[0];
        int blockZLo = zs[0];
        int blockZHi = zs[0];
        for (int i = 1; i < m; i++) {
            xLo = Math.min(xLo, xs[i]);
            xHi = Math.max(xHi, xs[i]);
            yLo = Math.min(yLo, ys[i]);
            yHi = Math.max(yHi, ys[i]);
            blockZLo = Math.min(blockZLo, zs[i]);
            blockZHi = Math.max(blockZHi, zs[i]);
        }
        xLo--;
        xHi++;
        yLo--;
        yHi++;
        zLo = Math.max(0, blockZLo - 1); // 地面是 z<0 的实心半空间，所以 z 方向不再往下扩
        zHi = blockZHi + 1;
        bottomIsOutside = zLo < blockZLo;
        ny = yHi - yLo + 1;
        nz = zHi - zLo + 1;
        int cells = (xHi - xLo + 1) * ny * nz;
        outside = cells;

        parent = new int[cells + 1];
        count = new int[cells];
        Arrays.fill(parent, -1);
        parent[outside] = outside;

        // 2. 初始局面：只有前 m-1 块已放好（即最后一次搭建之前的状态）
        for (int i = 0; i < m - 1; i++) {
            count[id(xs[i], ys[i], zs[i])]++;
        }

        // 3. 建并查集：所有空格子入集，与 x-1 / y-1 / z-1 三个方向上的空格子合并；
        //    区域边界上的空格子在方块包围盒之外，那片空气与无穷远连通，直接和外界合并
        for (int x = xLo; x <= xHi; x++) {
            for (int y = yLo; y <= yHi; y++) {
                for (int z = zLo; z <= zHi; z++) {
                    int cell = id(x, y, z);
                    if (count[cell] > 0) {
                        continue;
                    }
                    parent[cell] = cell;
                    if (x > xLo) {
                        unionIfFree(cell, id(x - 1, y, z));
                    }
                    if (y > yLo) {
                        unionIfFree(cell, id(x, y - 1, z));
                    }
                    if (z > zLo) {
                        unionIfFree(cell, id(x, y, z - 1));
                    }
                    if (x == xLo || x == xHi || y == yLo || y == yHi
                            || z == zHi || (z == zLo && bottomIsOutside)) {
                        union(cell, outside);
                    }
                }
            }
        }

        // 4. 从最后一块开始倒着判定：此时网格里正好是第 1..i-1 块
        for (int i = m - 1; i >= 0; i--) {
            int cell = id(xs[i], ys[i], zs[i]);
            // 4.1 这个位置上已经有方块了
            if (count[cell] > 0) {
                return false;
            }
            // 4.2 必须与地面或已放好的方块有面相邻
            if (!supported(xs[i], ys[i], zs[i])) {
                return false;
            }
            // 4.3 必须能从外界放进这个位置，即没有被围成封闭空间
            if (find(cell) != find(outside)) {
                return false;
            }
            // 5. 把第 i-1 块拿走（数量减到 0 才真正空出来），进入「只剩前 i-1 块」的局面
            if (i > 0) {
                int prev = id(xs[i - 1], ys[i - 1], zs[i - 1]);
                if (--count[prev] == 0) {
                    releaseCell(prev);
                }
            }
        }
        return true;
    }

    /** 格子重新变空：加入并查集，并和 6 个方向的空格子合并 */
    private static void releaseCell(int cell) {
        parent[cell] = cell;
        for (int d = 0; d < 6; d++) {
            int x = xOf(cell) + DX[d];
            int y = yOf(cell) + DY[d];
            int z = zOf(cell) + DZ[d];
            if (x < xLo || x > xHi || y < yLo || y > yHi || z < zLo || z > zHi) {
                continue; // 方块一定在区域内部，这里只是保险；z<zLo 就是地面
            }
            int neighbour = id(x, y, z);
            if (parent[neighbour] != -1) {
                union(cell, neighbour);
            }
        }
    }

    /** 是否至少有一个面与地面(z=0)或已放好的方块相邻 */
    private static boolean supported(int x, int y, int z) {
        if (z == 0) {
            return true; // 放在地面上
        }
        for (int d = 0; d < 6; d++) {
            int nx = x + DX[d];
            int ny2 = y + DY[d];
            int nz2 = z + DZ[d];
            if (nz2 < zLo || nx < xLo || nx > xHi || ny2 < yLo || ny2 > yHi) {
                continue; // 区域之外（只会是 z=-1 的地面）
            }
            if (count[id(nx, ny2, nz2)] > 0) {
                return true;
            }
        }
        return false;
    }

    private static int id(int x, int y, int z) {
        return ((x - xLo) * ny + (y - yLo)) * nz + (z - zLo);
    }

    private static int xOf(int cell) {
        return cell / (ny * nz) + xLo;
    }

    private static int yOf(int cell) {
        return (cell / nz) % ny + yLo;
    }

    private static int zOf(int cell) {
        return cell % nz + zLo;
    }

    private static int find(int a) {
        while (parent[a] != a) {
            parent[a] = parent[parent[a]];
            a = parent[a];
        }
        return a;
    }

    private static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra != rb) {
            parent[ra] = rb;
        }
    }

    /** 两个格子都还是空格子时才合并 */
    private static void unionIfFree(int a, int b) {
        if (parent[a] != -1 && parent[b] != -1) {
            union(a, b);
        }
    }
}
