package SortWork;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SelectionSort {
    public static void main(String[] args) {
        int[] sorts = {2,5,1,3,9,8,6,7,5};
        for (int i = 0;i<sorts.length-1;i++){
            int min = i;
            for (int j = i+1;j<sorts.length;j++){
                if (sorts[j] < sorts[min]){
                    min = j;
                }
            }
            int temp = sorts[min];
            sorts[min] = sorts[i];
            sorts[i] = temp;
        }
        for (int i : sorts){
            System.out.println(i);
        }
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        map.put(10,3);
        map.put(19,3);
        for (Integer i : map.keySet()){
            System.out.println(i + " " + map.get(i));
        }
    }
}
