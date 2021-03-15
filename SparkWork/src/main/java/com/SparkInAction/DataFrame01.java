package com.SparkInAction;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class DataFrame01 {
    public static void main(String[] args) {
        DataFrame01 frame01 = new DataFrame01();
//        frame01.Restaurants_Wake_Start();
        frame01.Restaurants_Durham_Start();
    }
    private void Restaurants_Wake_Start(){
        SparkSession spark = SparkSession.builder().appName("Restaurants in Wake Country").master("local").getOrCreate();
        // DataFrame在java中是当作DataSet<Row>来使用的
        Dataset<Row> df = spark.read().format("csv").option("header","true").load("src/main/resources/data/Restaurants_in_Wake_County.csv");
        System.out.println("get success");
        df.show(5);
        df.printSchema(); // 打印结构
        System.out.println("We have " + df.count() + " records");

        // 创建新列
        // lit("_")创建一个以_为值的列,df.col("state")获取df中列名为state的列数据
        df = df.withColumn("id",functions.concat(df.col("state"),functions.lit("_"),df.col("city"),functions.lit("_"),df.col("objectid")));
        df.show(5);
        df.printSchema();
    }
    private void Restaurants_Durham_Start(){
        SparkSession spark = SparkSession.builder().appName("Restaurant in Durham").master("local").getOrCreate();
        Dataset<Row> df = spark.read().format("json").load("src/main/resources/data/Restaurants_in_Durham_County_NC.json");
        df.show(5);
        df.printSchema();

        System.out.println(df.rdd().partitions().length);
//        df.repartition(4);
//        System.out.println(df.rdd().partitions().length);
    }

    /**
     * DataFrame进行union操作时的union()方法和unionByName()方法
     */
    // 两个DataFrame在做union操作时，如果使用union()方法，那么不会关心列名，就只会第一列跟第一列，第n列跟第n列组合一块、
    // 但是使用df1.unionByName(df2)方法的话，会按照列名进行组合。
}
