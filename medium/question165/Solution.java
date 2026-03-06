package question165;

/**
 * 165. 比较版本号
 * 给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
 * 比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。
 * 返回规则如下：
 * 如果 version1 < version2 返回 -1，
 * 如果 version1 > version2 返回 1，
 * 除此之外返回 0。
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        int len1 = version1.length();
        int len2 = version2.length();

        int i = 0;
        int j = 0;
        while(i < len1 || j < len2){
            // 获取'.'前的数字大小
            int num1 = 0;
            while(i < len1 && version1.charAt(i) != '.'){
                num1 = num1 * 10 + version1.charAt(i) - '0';
                i++;
            }

            int num2 = 0;
            while(j < len2 && version2.charAt(j) != '.'){
                num2 = num2 * 10 + version2.charAt(j) - '0';
                j++;
            }
            // 比较返回返回值
            if(num1 < num2) return -1;
            if(num1 > num2) return 1;
            // 跳过'.'开始下一个数
            i++;
            j++;
        }

        return 0;
    }
}
