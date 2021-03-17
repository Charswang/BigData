package com.SparkInAction.PITest;

import org.apache.spark.api.java.function.ReduceFunction;

import java.io.Serializable;

public class DataReducer implements ReduceFunction<Integer>, Serializable {
    @Override
    public Integer call(Integer x, Integer y) throws Exception {
        return x + y;
    }
}
