package com.SparkInAction.PITest;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;

import java.io.Serializable;

public class DataMapper implements MapFunction<Row,Integer>, Serializable {
    @Override
    public Integer call(Row row) throws Exception {
        double x = Math.random() * 2 - 1;
        double y = Math.random() * 2 - 1;
        return (Math.pow(x,2) + Math.pow(y,2) <= 1) ? 1 : 0;
    }
}
