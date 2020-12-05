package SortWork;


public class LeetcodeTest {
    public static void main(String[] args) {
        String s = "foo\\bar\\ppp . % ";
        System.out.println(s);
        String[] strs = s.split("\\\\");
        System.out.println(strs.length);
        for (String str : strs){
            System.out.println("--" + str);
        }

        System.out.printf("s+");
    }
}
