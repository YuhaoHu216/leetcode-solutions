package question415;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 */
class Solution {
    public String addStrings(String num1, String num2) {
        //
        StringBuilder result = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;

        while(i >= 0 || j >=0){
            // 如果长度不够就要补零
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            // 得到进位 数字
            int temp = n1 + n2 + carry;
            int number = temp % 10;
            carry = temp / 10;
            result.append(number);
            // 指针更新
            i--;
            j--;
        }
        // 如果最后还有进位就要多加一位
        if(carry == 1){
            result.append(1);
        }
        // 结果要反转并转成字符串
        return result.reverse().toString();
    }
}
