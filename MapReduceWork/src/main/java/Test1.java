import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Test1 {
    public static void main(String[] args) {
//        String str = Integer.toString(123);
//        StringBuffer sb = new StringBuffer(str);
//        System.out.println(sb.reverse());
//        int[] arr = new int[]{1,2,5,3,2,4,8,5,4,7,9,6};
//        Arrays.sort(arr);
//        for (int i = 0;i<arr.length;i++){
//            System.out.println(arr[i]);
//        }
//        System.out.println(Math.pow(4,2));
////
//        Map<String,String> map = new HashMap<String, String>();
//        map.put("1","1");
//        map.put("3","1");
//        map.put("2","1");
//        for (String s : map.keySet()){
//            System.out.println(s + map.get(s));
//        }
//        for (Map.Entry val:map.entrySet()){
//            System.out.println(val.getKey().toString() + val.getValue().toString());
//        }
//
//        List<String> l = new ArrayList<String>();
//        l.add("1");
//        l.add("1");
//        l.add("1");
//        System.out.println(l.size());
//        String[] objects = (String[]) l.toArray();

//        Stack<String> ss = new Stack<String>();
//        ss.add("3");
//        ss.add("2");
//        ss.add("4");
//        System.out.println(ss.pop());
//        System.out.println(ss.pop());
//        System.out.println(ss.size());
//
//        List<Integer> l1 = new ArrayList<Integer>();
//        l1.add(2);
//        l1.add(7);
//        l1.add(5);
//        l1.add(9);
//        l1.add(0);
//        Collections.sort(l1);
//        for (Integer i : l1){
//            System.out.println(i);
//        }
//        Collections.reverse(l1);
//        for (Integer i : l1){
//            System.out.println(i);
//        }
//
//        Queue<String> qu = new LinkedBlockingDeque<String>();
//        qu.offer("1");
//        qu.offer("2");
//        qu.offer("3");
//        /*for (String s1 : qu){
//            System.out.println(s1);
//        }*/
//        System.out.println(qu.poll());
//
//        System.out.println("123".substring(2));
//
//        char a = 'a';
//        char b = 'a';
//        System.out.println(a==b);
        Animal cat = new Cat();
        Animal dog = new Dog();
        cat.eat();
        System.out.println(cat.getAge());
        System.out.println(dog.getAge());

        dog.eat();
    }
    public static void show(Animal animal){
        animal.eat();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Animal{
    private String name = "animal name";
    private int age = 0;

    public void eat(){
        System.out.println("animal eat");
    }
}

class Dog extends Animal{
    private String name = "wangwang";
    private int age = 1;

    public void eat(){
        System.out.println("dog eat bone");
    }
}

class Cat extends Animal{
    private String name = "miaomiao";
    private int age = 2;

    public void eat(){
        System.out.println("cat eat fish");
    }
}