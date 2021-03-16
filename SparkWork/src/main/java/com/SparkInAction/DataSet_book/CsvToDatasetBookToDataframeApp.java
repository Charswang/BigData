package com.SparkInAction.DataSet_book;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import org.apache.spark.sql.functions;

import java.io.Serializable;

// 这里加不加实现序列化接口好像没有什么影响
public class CsvToDatasetBookToDataframeApp implements Serializable {
    public static void main(String[] args) {
        CsvToDatasetBookToDataframeApp demo = new CsvToDatasetBookToDataframeApp();
        demo.start();
    }
    public void start(){
        SparkSession spark = SparkSession.builder().appName("").master("local").getOrCreate();
        String filePath = "src/main/resources/data/books.csv";
        // inferSchema表示是否支持从数据中自动导出数据的Schema，因为不加这一项，所有元素都是按照String类进行读取的。
        Dataset<Row> df01 = spark.read().format("csv").option("header", "true").option("inferSchema", "true").load(filePath);
        System.out.println("***Books in DataFrame***");
        df01.show(5);
        df01.printSchema();
        System.out.println("-----------------分割线--------------");

        // DataFrame转DataSet使用的是map函数
        // map中的第一个参数，是要实现MapFunction<T,U>的接口中的call方法
        Dataset<Book> ds01 = df01.map(new BookMapper(), Encoders.bean(Book.class));
        System.out.println("***Books in DataSet***");
        ds01.show(5,17);
        ds01.printSchema();
        System.out.println("-----------------分割线--------------");

        // 由DataSet转为DataFrame对releaseDate格式进行一些调整
        Dataset<Row> df02 = ds01.toDF();
        df02 = df02.withColumn("releaseDateAsString", functions.concat(functions.expr("releaseDate.year + 1900"),
                functions.lit("-"), functions.expr("releaseDate.month + 1"), functions.lit("-"), df02.col("releaseDate.date")));
        df02.show(5);
        df02.printSchema();
        System.out.println("-----------------分割线--------------");

        // 修改releaseDateAsString中属性所属类
        df02 = df02.withColumn("releaseDateAsData",functions.to_date(df02.col("releaseDateAsString"),"yyyy-MM-dd")).drop("releaseDateAsString");
        df02.show(5);
        df02.printSchema();

    }
}
