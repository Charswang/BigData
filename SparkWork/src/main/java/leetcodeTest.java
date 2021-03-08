import java.util.*;

public class leetcodeTest {
    public static void main(String[] args) {

        String a = "aabb";
        System.out.println(a.substring(0,0));
        System.out.println(buddyStrings("ab","ba"));

    }
    public static boolean buddyStrings(String a, String b) {
        if(a.length()!=b.length() || a.length()==0 || b.length()==0){
            return false;
        }
        ArrayList<Character> list = new ArrayList<Character>();
        Collections.sort(list);
        Set<Character> s = new HashSet<Character>();
        StringBuilder ab = new StringBuilder();

        if(a.equals(b)){
            for(int i = 0;i<a.length();i++){
                if(list.contains(a.charAt(i))){
                    return true;
                }
                list.add(a.charAt(i));
            }
            return false;
        }
        ArrayList<Integer> ll = new ArrayList<Integer>();
        for(int i = 0;i<a.length();i++){
            if(a.charAt(i)!=b.charAt(i)){
                ll.add(i);
            }
        }
        if(ll.size()==2){
            a = a.substring(0,ll.get(0)) + a.charAt(ll.get(1)) + a.substring(ll.get(0)+1,ll.get(1)) + a.charAt(ll.get(0)) + a.substring(ll.get(1)+1);
        }
        if(a.equals(b)){
            return true;
        }
        return false;
    }
}
