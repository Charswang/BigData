package test.leetcode;

import java.util.*;

public class Test {
    public static void main(String[] args) {
//        System.out.println(Character.isLetter('5'));
//        System.out.println(reverseOnlyLetters("ab-cd"));  // 917. 仅仅反转字母
//        System.out.println(isLongPressedName("vtkgn", "vttkgnn")); // 925. 长按键入
        /*String[] strs = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        System.out.println(numUniqueEmails(strs));*/
//        System.out.println(gcdOfStrings("TAUXXTAUXXTAUXXTAUXXTAUXX","TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX")); //1071. 字符串的最大公因子
//        System.out.println(defangIPaddr("192.168.246.132")); // 1108、IP 地址无效化
//        System.out.println(maxNumberOfBalloons("loonbalxballpoon")); // 1189. “气球” 的最大数量
//        System.out.println(balancedStringSplit("RLRRLLRLRL"));  // 1221. 分割平衡字符串
//        System.out.println(freqAlphabets("10#11#12"));  // 1309. 解码字母到整数映射
//        System.out.println(sortString("ggggggg")); // 1370. 上升下降字符串
//        int[] height = {1,1};
//        System.out.println(maxArea(height));
        int[] nums = {1,-1,-1,0};
        System.out.println(threeSum(nums));
    }

    /**
     * 917. 仅仅反转字母
     * @param S
     * @return String
     */
    static String reverseOnlyLetters(String S) {
        char[] chars = S.toCharArray();
        int i = 0,j = chars.length-1;
        while(i < j){
            if (!Character.isLetter(chars[i])){
                i++;
                continue;
            }
            if (!Character.isLetter(chars[j])){
                j--;
                continue;
            }
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : chars){
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 925. 长按键入
     * @param name
     * @param typed
     * @return boolean
     */
    static boolean isLongPressedName(String name, String typed) {
        int name_start = 0,typed_start = 0;
        char[] name_chars = name.toCharArray();
        char[] typed_chars = typed.toCharArray();
        while(name_start<name_chars.length && typed_start<typed_chars.length){
            if(name_chars[name_start] == typed_chars[typed_start]){
                name_start++;
                typed_start++;
                continue;
            }
            if(name_start!=0 && name_chars[name_start-1] == typed_chars[typed_start]){
                typed_start++;
            }else{
                return false;
            }
        }
        if(name_start<name_chars.length){
            return false;
        }
        if(typed_start<typed_chars.length){
            for(int i = typed_start-1;i<typed_chars.length;i++){
                if(typed_chars[i]!=typed_chars[typed_start-1]){
                    return false;
                }
            }
        }
        return true;
    }

    static int numUniqueEmails(String[] emails) {
        Set set = new HashSet<String>();
        for(int i = 0;i < emails.length;i++){
            String s = emails[i].substring(0,emails[i].indexOf('@')).replaceAll("\\.","");
            if (s.indexOf('+')!=-1){
                s = s.substring(0,s.indexOf('+'));
            }
            StringBuilder sb = new StringBuilder();
            sb.append(s + emails[i].substring(emails[i].indexOf('@')));
            set.add(sb.toString());
            System.out.println(sb.toString());
        }
        return set.size();
    }

    /**
     * 1071. 字符串的最大公因子
     * 欧几里得算法，求最大公约数
     * @param str1
     * @param str2
     * @return String
     */
    static String gcdOfStrings(String str1, String str2) {
        if (!(str1+str2).equals(str2+str1)){ // 直接判断是两者是否有最大公约数
            return "";
        }
        return str1.substring(0,gcd(str1.length(),str2.length()));
    }
    // 欧几里得算法
    static int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }

    /**
     * 1108、IP 地址无效化
     * @param address
     * @return String
     */
    static String defangIPaddr(String address) {
        // 注意字符.的转义字符
        return address.replaceAll("\\.","[\\.]");
    }

    /**
     * 1189. “气球” 的最大数量
     * @param text
     * @return int
     */
    static int maxNumberOfBalloons(String text) {
        String ballon = "balon";
        int len = text.length();
        Map<Character,Integer> map = new TreeMap<Character,Integer>();
        for(int i = 0;i < len;i++){
            if(ballon.indexOf(text.substring(i,i+1))!=-1){
                if(map.keySet().contains(text.charAt(i))){
                    map.put(text.charAt(i),map.get(text.charAt(i))+1);
                }else{
                    map.put(text.charAt(i),1);
                }
            }
        }
        int result = 0;
        if(map.keySet().size()<5){
            return result;
        }
        result = Math.min(Math.min(map.get('a'),map.get('b')),map.get('n'));
        for(int i=result;i>0;i--){
            if(map.get('l')/2 < result || map.get('o')/2 < result){
                result--;
            }else{
                break;
            }
        }
        return result;
    }

    /**
     * 1221. 分割平衡字符串
     * 分别对R，L出现的次数进行排序。之后比较每当R最大值遇到L最大值时，进行分割
     * 例子：RLRRLLRLRL -- 1123234455 -- 11 2323  44  55
     *      RLLLLRRRLR -- 1123423455 -- 11 234234 55
     * @param s
     * @return int
     */
    static int balancedStringSplit(String s) {
        int[] temp = new int[s.length()];
        int len_R = 0,len_L = 0;
        for(int i = 0;i < s.length();i++){
            if(s.charAt(i)=='R'){
                temp[i] = ++len_R;
            }else{
                temp[i] = ++len_L;
            }
        }
        int result = 0;
        int max = temp[0];
        for(int i = 1;i < temp.length;i++){
            if(max==temp[i]){
                result++;
            }else if(max<temp[i]){
                max = temp[i];
            }else{
                continue;
            }
        }
        return result;
    }

    /**
     * 1309. 解码字母到整数映射
     * 从后向前查看，遇到#减3，否则减1。
     * 字符转数字，数字转字符；StringBuffer插入添加
     * @param s
     * @return String
     */
    static String freqAlphabets(String s) {
        int len = s.length()-1;
        StringBuilder sb = new StringBuilder();
        while(len>=0){
            if(s.charAt(len)=='#'){
                int i = Integer.parseInt(s.substring(len-2,len));
                sb.insert(0,(char)(96+i));
                len = len - 3;
            }else{
                int i = Integer.parseInt(s.substring(len,len+1));
                sb.insert(0,(char)(96+i));
                len = len - 1;
            }
        }
        return sb.toString();
    }

    static int removePalindromeSub(String s) {
        if(s.length()==0){
            return 0;
        }
        int result = 0;
        int f = 0;
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        while(f<s.length()){
            sb.append(s.substring(f,len));
            if(sb.toString().equals(sb.reverse().toString())){
                result++;
                f = len;
                len = s.length();
            }else{
                len = len - 1;
            }
            sb.setLength(0);
        }
        return result;
    }

    /**
     * 1370. 上升下降字符串
     * @param s
     * @return
     */
    static String sortString(String s) {
        Map<Character,Integer> map = new TreeMap<Character,Integer>();
        for(int i = 0;i < s.length();i++){
            if(map.keySet().contains(s.charAt(i))){
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }else{
                map.put(s.charAt(i),1);
            }
        }
        for (char c : map.keySet()){
            System.out.println(c + "--" + map.get(c));
        }
        StringBuilder result = new StringBuilder();
        int count = 0;
        int flag = 0;
        while(flag<map.keySet().size()){
            flag = 0;
            StringBuilder temp = new StringBuilder();
            for(char c : map.keySet()){
                if(map.get(c)>0){
                    temp.append(c);
                    map.put(c,map.get(c)-1);
                }else{
                    flag++;
                }
            }
            if(count%2==0){
                result.append(temp.toString());
            }else{
                result.append(temp.reverse().toString());
            }
            count++;
        }
        return result.toString();
    }

    /**
     * 11、盛最大水的容器
     * @param height
     * @return
     */
    static int maxArea(int[] height) {
        // 暴力
        /*int len = height.length;
        int max = 0;
        for(int i = 0;i < len-1;i++){
            for(int j = i+1;j < len;j++){
                int h = Math.min(height[i],height[j]);
                int w = j - i;
                if(h * w > max){
                    max = h * w;
                }
            }
        }
        return max;*/


        // 双指针
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right){
            max = Math.max(max,(right-left)*Math.min(height[left],height[right]));
            // 在左边向右边或者右边向左边移动一次的话，要想可能得到的面积更大，那么就要移动其中较小的一根，才能有可能得到更大的面积
            // 因为下一次的底会变小，所以就要想办法让高变得更长一些。
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return max;
    }

    /**
     * 15、三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * @param nums
     * @return List<List<Integer>>
     */
    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list2 = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i = 0;i < nums.length;i++){
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            int t = nums[i];
            int m = i + 1;
            int n = nums.length - 1;
            while(m < n){
                if(nums[m] + nums[n] + t > 0){
                    n--;
                }else if(nums[m] + nums[n] + t < 0){
                    m++;
                }else{
                    List<Integer> list1 = new ArrayList<Integer>();
                    list1.add(nums[i]);
                    list1.add(nums[m]);
                    list1.add(nums[n]);
                    list2.add(list1);
                    while(m < n && nums[m]==nums[m+1]){
                        m++;
                    }
                    while(n > m && nums[n]==nums[n-1]){
                        n--;
                    }
                    m++;
                    n--;
                }
            }
        }
        return list2;
    }

    /**
     * 16. 最接近的三数之和
     * 这里使用的方法，与第15题的方法一样。
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int min_dif = 0x7fffffff;
        int result = 0;
        // for循环选定一个三个数字中的其中一个
        for(int i = 0;i < len;i++){
            // 去除重复的三个数字中的第一个数字
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int s = i + 1;
            int e = len - 1;
            // 开始对后面的数进行双指针循环
            while(s < e){
                // 如果两者差值小于当前差值min_dif，则更新最小差值，更新result
                if(min_dif > Math.abs(target - nums[i] - nums[s] - nums[e])){
                    min_dif = Math.abs(target - nums[i] - nums[s] - nums[e]);
                    result = nums[i] + nums[s] + nums[e];
                }
                // 如果三数之和大于目标值，可以将后面的指针向前移动一个位置，将三数之和进行减少操作
                if(nums[i] + nums[s] + nums[e] > target){
                    e--;
                }else if(nums[i] + nums[s] + nums[e] < target){  // 如果三数之和小于目标值，可以将后面的指针向后移动一个位置，将三数之和进行增加操作
                    s++;
                }else{
                    return target;  // 如果三数之和等于目标值，直接返回目标值
                }

            }
        }
        return result;
    }
}


