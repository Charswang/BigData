package SortWork;

public class InsertionSort {
    public static void main(String[] args) {
        int[] sorts = {2,5,1,3,9,8,6,7,5};
        for (int i = 1;i<sorts.length;i++){
            for (int j = i;j>0;j--){
                if (sorts[j] < sorts[j-1]){
                    int temp = sorts[j];
                    sorts[j] = sorts[j-1];
                    sorts[j-1] = temp;
                }
            }
        }
        for (int i : sorts){
            System.out.println(i);
        }
    }
}
