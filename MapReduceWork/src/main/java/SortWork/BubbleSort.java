package SortWork;

public class BubbleSort {
    public static void main(String[] args) {
        int[] sorts = {2,5,1,3,9,8,6,7,5};
        for (int i = 0;i < sorts.length-1;i++){
            for (int j = 0;j < sorts.length-i-1;j++){
                if (sorts[j] > sorts[j+1]){
                    int temp = sorts[j+1];
                    sorts[j+1] = sorts[j];
                    sorts[j] = temp;
                }
            }
        }
        for (int i : sorts){
            System.out.println(i);
        }
    }
}
