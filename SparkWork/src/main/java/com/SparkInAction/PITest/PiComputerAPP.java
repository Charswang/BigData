package com.SparkInAction.PITest;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.ReduceFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PiComputerAPP implements Serializable {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        PiComputerAPP PI = new PiComputerAPP();
        PI.start(10);
        long t2 = System.currentTimeMillis();
        System.out.println("The run time is " + (t2 - t1) + "ms");
    }
    private void start(int slices){
        int randNums = 10000 * slices;
        SparkSession spark = SparkSession.builder().appName("PI COMPUTER TEST").master("local").getOrCreate();
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 0;i < randNums;i++){
            l.add(i);
        }
        Dataset<Row> df = spark.createDataset(l, Encoders.INT()).toDF();

        // 不使用java lambda表达式的情况下
        /*Dataset<Integer> ds = df.map(new DataMapper(), Encoders.INT());
        int sum = ds.reduce(new DataReducer());*/

        // 使用lambda表达式的写法
        // 使用lambda时，如果实现了接口，那么表达式前面要加上(所实现的接口名称)? / 不太确定，好像没加也可以跑的通，但是map方法那里会标红
        Dataset<Integer> ds = df.map((MapFunction<Row,Integer>) status ->{
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            return (Math.pow(x,2) + Math.pow(y,2)) <= 1 ? 1 : 0;
        },Encoders.INT());
        int sum = ds.reduce((ReduceFunction<Integer>) (x,y) -> x + y);

        System.out.println(sum);
        System.out.println("The result about PI is " + 4.0 * sum / randNums);
        spark.stop();
    }
}
