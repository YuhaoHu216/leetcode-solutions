import java.util.*;

/**
 * 小明去花园采摘鲜花。花园里共有 n 朵花，每朵花都有两个属性：
 * t[i]：第 i 朵花的种类编号
 * d[i]：第 i 朵花的新鲜度
 * 现在要求从这 n 朵花中选择 恰好 k 朵花。
 * 选择的 k 朵花会获得一个总评分，评分计算方式为：
 * 新鲜度得分：所选 k 朵花的新鲜度之和；
 * 种类奖励：设选出的花中共有 x 种不同的花，则额外获得 x² 分。
 * 因此，总评分为：
 * $$ \text{score}=\sum d_i+x^2 $$
 * 请你选择 k 朵花，使最终得到的总评分最大，并输出最大评分。
 *
 * A了18%，runtime error了
 */
class Solution2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] param = firstLine.split(" ");
        int n = Integer.parseInt(param[0]);
        int k = Integer.parseInt(param[1]);

        int[] t = new int[n];
        int[] d = new int[n];
        // 加载花朵的编号和新鲜度
        for(int i = 0; i < n; i++){
            String line = sc.nextLine();
            String[] temp = line.split(" ");
            int ti = Integer.parseInt(temp[0]);
            int di = Integer.parseInt(temp[1]);

            t[i] = ti;
            d[i] = di;
        }

        // 回溯得到所有 n 选 k 的搭配然后进行最大评分的迭代
        List<Integer> cur = new ArrayList<>();
        int[] result = {0};
        boolean[] visited = new boolean[n];
        backTracking(k,t,d,cur,result,visited);

        System.out.println(result[0]);
    }

    static void backTracking(int k, int[] t, int[] d, List<Integer> cur, int[] result,boolean[] visited){
        if(cur.size() == k){
            // cur 中存的是每朵花的下标
            // 已经拿到了k朵花开始计算分数
            Set<Integer> set = new HashSet<>(); // 用一个集合得到花的种类
            int curScore = 0;
            for(int i : cur){
                set.add(t[i]);
                curScore += d[i];
            }
            int size = set.size();
            curScore += size * size;
            result[0] = Math.max(curScore,result[0]);
        }
        for(int i = 0; i < t.length; i++){
            if(!visited[i]){
                visited[i] = true;
                cur.add(i);
                backTracking(k,t,d,cur,result,visited);
                cur.remove(cur.size() - 1);
                visited[i] = false;
            }
        }
    }

}

