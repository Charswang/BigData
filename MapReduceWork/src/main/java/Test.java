import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Test {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc1 = new Scanner(System.in);
        String str1 = in.readLine();
        int[] num1 = new int[2];
        for (int i=0;i<2;i++){
            num1[i] = Integer.parseInt(str1.split(" ")[i]);
        }

        int[] scores = new int[num1[0]];
        //Scanner sc2 = new Scanner(System.in);
        String str2 = in.readLine();
        for(int i=0;i<num1[0];i++){
            scores[i] = Integer.parseInt(str2.split(" ")[i]);
        }
        //Scanner sc3 = new Scanner(System.in);
        String str3 = "";
        while ((str3=in.readLine())!=null){
            String flag = str3.split(" ")[0];
            if("Q".equals(flag)){
                int start = Integer.parseInt(str3.split(" ")[1]);
                int end = Integer.parseInt(str3.split(" ")[2]);
                int max = scores[start-1];
                for(int j = start-1;j<end;j++){
                    if (max < scores[j]){
                        max = scores[j];
                    }
                }
//                for (int t = 0;t<scores.length;t++){
//                    System.out.printf(scores[t] + " ");
//                }
                System.out.println(max);
            }else{
                int index = Integer.parseInt(str3.split(" ")[1]);
                int score = Integer.parseInt(str3.split(" ")[2]);
                scores[index - 1] = score;
            }
        }
        in.close();
//            str3 = in.readLine();

    }
}
