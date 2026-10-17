package D1017;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Question1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        in.nextLine();

        String[] input = new String[q];
        for(int i = 0; i < q; i++){
            input[i] = in.nextLine();
        }

        // 输出成功记录数，被拒绝记录数，所有成功出库停留时长之和，最后仍在仓库中的物品数量
        Map<String,Integer> map = new HashMap<>();
        long success = 0; 
        long refuse = 0;
        long time = 0;

        for(int i = 0; i < q; i++){
            String s = input[i];
            String[] temp = s.split(" ");
            
            if(temp[0].equals("IN")){
                if(map.containsKey(temp[1])){
                    refuse++;
                }else{
                    success++;
                    map.put(temp[1],i);
                }
                
            }

            if(temp[0].equals("OUT")){
                if(map.containsKey(temp[1])){
                    success++;
                    time += (i - map.get(temp[1]));
                    map.remove(temp[1]);
                    
                }else{
                    refuse++;
                }
            }
        }

        System.out.println(success + " " + refuse + " " + time + " " + map.size());
    }
}