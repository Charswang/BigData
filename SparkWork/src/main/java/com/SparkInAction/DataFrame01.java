package com.SparkInAction;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

import java.util.List;

public class DataFrame01 {
    public static void main(String[] args) {
        DataFrame01 frame01 = new DataFrame01();
//        frame01.Restaurants_Wake_Start();
//        frame01.Restaurants_Durham_Start();
        frame01.test();
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

    private void test(){
        /**
         * spark正常读取json的时候，json文件中的每个对象只占一行
         */
        /*SparkSession spark = SparkSession.builder().appName("Test").master("local").getOrCreate();
        // DataFrame在java中是当作DataSet<Row>来使用的
        Dataset<Row> df = spark.read().format("json").load("src/main/resources/data/page1x100.json");
        Dataset<Row> df1 = spark.read().option("multiLine",true).format("json").load("src/main/resources/data/page1x100.json");
        System.out.println("get success");
        df1.printSchema();
        df1.show(5);
        System.out.println(df1.select("data").toString());*/

        /**
         * 使用到HuTool中的FILEUtil JSONUtil JSONArray JSONObject
         * json文件中的一个对象应该是一行才能运行
         * json转csv，但是没成功，应该是出错在objects上
         */
        List<String> list = FileUtil.readUtf8Lines("D:/WangHao/Works/BigData/SparkWork/src/main/resources/data/test2.json");
        for (int i = 0;i < list.size();i++){
            String s = list.get(i);
            JSONObject jsonObject = JSONUtil.parseObj(s);
            Integer page = jsonObject.getInt("page");
            Integer rows = jsonObject.getInt("rows");
            Integer total = jsonObject.getInt("total");
            JSONArray data = jsonObject.getJSONArray("data");
            int size = data.size();
            System.out.println("数据条数：" + size);
            JSONArray objects = new JSONArray(size);

            String deal_date = null;
            String close_date = null;
            String deal_type = null;
            String company_name = null;

            for (int j = 0;j < size;j++){
                JSONObject jsonObject1 = (JSONObject) data.get(j);
                deal_date = jsonObject1.get("deal_date").toString();
                close_date = jsonObject1.get("close_date").toString();
                deal_type = jsonObject1.getStr("deal_type");
                company_name = jsonObject1.getStr("company_name");
//                System.out.println(deal_date + "--" + close_date + "--" + deal_type +"--" + company_name);

                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.putOnce("deal_date",deal_date);
                jsonObject2.putOnce("close_date",close_date);
                jsonObject2.putOnce("deal_type",deal_type);
                jsonObject2.putOnce("company_name",company_name);
                objects.add(j,jsonObject2);
            }
            String line = deal_date + "\t" + close_date + "\t" + deal_type + "\t" + company_name + "\t" + objects;
            System.out.println(line);
            FileUtil.appendUtf8Lines(objects,"D:/WangHao/Works/BigData/SparkWork/src/main/resources/data/plan.csv");
//            FileUtil.appendUtf8String(line+"\n","D:/WangHao/Works/BigData/SparkWork/src/main/resources/data/plan.json");
            System.out.println("done");
        }
    }
}
