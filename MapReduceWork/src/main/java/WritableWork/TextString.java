package WritableWork;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class TextString {
    public static void main(String[] args) {
        //Writable集合类
        ArrayWritable arrayWritable = new ArrayWritable(Text.class);
        Text[] t = new Text[3];
        t[0] = new Text("one");
        t[1] = new Text("two");
        t[2] = new Text("three");
        arrayWritable.set(t);
        for (int i = 0;i<t.length;i++){
            System.out.println(arrayWritable.getValueClass());
        }
//        System.out.println(arrayWritable);
    }
}
