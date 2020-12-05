package SortWork;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShellSort {
    public static void main(String[] args) {
//        System.out.println(Integer.parseInt("000"));
//        Stack<String> stack = new Stack<String>();
//        stack.add("0");
//        stack.add("1");
//        System.out.println(stack.toString());
//        System.out.println(stack.lastElement());
//
//        char[] arr = "qwer".toCharArray();
//        String s = String.valueOf(arr[0]);
//        for (String s1:stack){
//            System.out.println(s1);
//        }
        System.out.println(removeKdigits("1173",2));
        String s = "000000232421";
        BigInteger q = new BigInteger(s);
        System.out.println(q);

        int[] arr = {0,0,0,0,0,0};
        test(arr);
        System.out.println(arr[0]);

        int[][] nums = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] nums2 = reconstructQueue(nums);
        for (int[] i : nums2){
            for (int j : i){
                System.out.print(j + " ");
            }
            System.out.println("\n");
        }
    }
    public static void test(int[] arr){
        arr[0] = 1;
    }
    public static String removeKdigits(String num, int k) {
        if (num.length() == k){
            return "0";
        }
        Stack<String> stack = new Stack<String>();
        char[] chars = num.toCharArray();
        for (char c : chars){
            while (k>0 && stack.size()>0 && Integer.parseInt(stack.lastElement()) > Integer.parseInt(String.valueOf(c))){
                stack.pop();
                k--;
            }
            stack.add(String.valueOf(c));
        }
        String s = "";
        int remain = num.length() - k;
        for(String str : stack){
            if(remain > 0){
                s += str;
            }else{
                break;
            }
            remain--;
        }
        s = String.valueOf(Integer.parseInt(s));
        return s;
    }
    public static int[][] reconstructQueue(int[][] people) {
        //先排序，再插入。
        //解题思路：可以先将最高的人放到一个list里面，然后最高的人按照k再进行升序排序
        //然后再从剩下的人里面挑出最高的并且k最小的进行插入，插入的位置就是当前人的k。
        //所以，可以先排序再插入
        //排序：对h降序排序，h相等时对k升序排序
        //插入：从排序好的数组中拿出最前面的一个，进行插入，插入的位置是当前人的k。因为肯定会有一个h最大的人的k为0的。
        //例如：[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
        //排序：[[7,0], [7,1], [6,1], [5,0], [5,2], [4,4]]
        //插入：[[7,0]]
        //      [[7,0],[7,1]]
        //      [[7,0],[6,1],[7,1]]
        //      [[5,0],[7,0],[6,1],[7,1]]
        //      [[5,0],[7,0],[5,2],[6,1],[7,1]]
        //      [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
        //排序
        //这里是用到了Arrays.sort。再方法里面用到了comparator.使用lambda表达式进行描述。
        //o1-o2表示升序，o2-o1表示降序。
        Arrays.sort(people,(o1,o2)->o1[0]==o2[0]?o1[1]-o2[1]:o2[0]-o1[0]);
        List<int[]> l = new ArrayList<int[]>();
        for (int[] i : people){
            l.add(i[1],i);
        }
        int q = Integer.MAX_VALUE;
        Math.abs(-1);
        //list.toArray(要转成数组的形式比如二维int数组)
        return l.toArray(new int[people.length][2]);
    }
}
