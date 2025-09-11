package question57;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * String[] letter_map = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
 */
class Solution {

    // 创建数字对应字母映射(数组比集合更省时间)
    String[] letter_map = {"","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();    // 用来存放所有组合结果
        StringBuilder str = new StringBuilder();    // 用来构建每个组合
        int index = 0;                              // 表示当前处理到digits的第几位

        // 空指针防御
        if(digits == null || digits.length() == 0){
            return new ArrayList<String>();
        }
        backTracking(result,str,index,digits);

        return result;
    }

    void backTracking(List<String> result,StringBuilder str,int index,String digits){

        // 当当前的组合长度等于给的数字个数就表示当前组合拼接完毕
        if(str.length() == digits.length()){
            result.add(str.toString());
            return;
        }

        // 获取当前数字对应的字母集
        String letters = letter_map[digits.charAt(index) - '0'];

        for(int i = 0; i < letters.length(); i++){
            str.append(letters.charAt(i));              // 做选择
            backTracking(result,str,index+1,digits);    // 递归
            str.deleteCharAt(str.length() - 1);         //回溯
        }
    }
}
