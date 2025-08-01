package question82;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i =0; i < numRows; i++){
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j <= i;j++){
                if(j==0 || j==i){
                    row.add(1);
                }else{
                    int left = result.get(i-1).get(j-1);
                    int right = result.get(i-1).get(j);
                    row.add(left + right);
                }
            }
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generate(5));
    }
}
