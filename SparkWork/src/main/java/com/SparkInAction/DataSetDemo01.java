package com.SparkInAction;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;

public class DataSetDemo01 {
    public static void main(String[] args) {
        DataSetDemo01 demo = new DataSetDemo01();
        demo.DataSetStart();
    }
    private void DataSetStart(){
        SparkSession spark = SparkSession.builder().appName("DataSet Start").master("local").getOrCreate();
        String[] setList = new String[] {"tom","lisa","john"};
        List<String> strings = Arrays.asList(setList);

        // 创建DataSet
        // DataSet<String>中的String是POJO类
        Dataset<String> ds01 = spark.createDataset(strings, Encoders.STRING());
        ds01.show();
        ds01.printSchema();
        System.out.println("-------------------分割线-----------------");

        // 转DataFrame
        Dataset<Row> df01 = ds01.toDF("Name");
        df01.show();
        df01.printSchema();
    }
}
