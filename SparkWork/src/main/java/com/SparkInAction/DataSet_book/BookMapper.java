package com.SparkInAction.DataSet_book;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

// 这里加不加实现序列化接口好像没有什么影响
public class BookMapper implements MapFunction<Row,Book>, Serializable {
    @Override
    public Book call(Row row) throws Exception {
        Book b = new Book();
        b.setAuthorId(row.<Integer>getAs("authorId"));
        b.setId(row.<Integer>getAs("id"));
        b.setTitle(row.<String>getAs("title"));
        b.setLink(row.<String>getAs("link"));
        String dateAsString = row.<String>getAs("releaseDate");
        if (dateAsString!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy"); // 这里的M/d/yy是按照数据集中的格式来匹配的
            Date date = sdf.parse(dateAsString);
            b.setReleaseDate(date);
        }
        return b;
    }
}
