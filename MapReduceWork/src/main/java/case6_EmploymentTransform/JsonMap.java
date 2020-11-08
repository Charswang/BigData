package case6_EmploymentTransform;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class JsonMap extends Mapper<LongWritable, Text, Employment, NullWritable> {

    //将MySQL中的province表中要替换的数据，利用键值对放到map中。
    Map<String,String> provinceMap = new HashMap<String, String>();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        Connection conn = DBHelper.getConnection();
        if (conn != null){
            String sql = "select * from province";
            try {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    String city_code = resultSet.getString(1);
                    String city_name = resultSet.getString(2);
                    provinceMap.put(city_code,city_name);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //解析json文件
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        //解析Json数据--利用fastJson包
        //但是在这个程序里面，每条json数据要保证是写在一行里的，因为map读的时候是读一行的，否则，读不完数据，利用fastJson转数据的时候会报错的
        //并且这里的每条json数据，不能像正常的json文件那样把每条json数据用逗号分开。
        // 否则转数据的时候会报错“java.lang.Exception: com.alibaba.fastjson.JSONException: not close json text, token : ,”
        JSONObject jsonObject = JSONObject.parseObject(line);

        //声明一个String数组，长度为Employment对象属性个数
        String[] data = new String[14];
        data[0] = jsonObject.getString("id");
        data[1] = jsonObject.getString("company_name");
        data[2] = jsonObject.getString("eduLevel_name");
        data[3] = jsonObject.getString("emplType");
        data[4] = jsonObject.getString("jobName");
        String salary =  jsonObject.getString("salary");
        //处理数据中的salary
        //mk-nk:(m+n)/2    其余的即为0
        if (salary.contains("K-")){
            Double a = Double.parseDouble(salary.substring(0,salary.indexOf("K")));
            Double b = Double.parseDouble(salary.substring(salary.indexOf("-")+1,salary.lastIndexOf("K")));
            data[5] = (a+b)/2 + "";
        }else{
            data[5] = "0";
        }
        data[6] = jsonObject.getString("createDate");
        data[7] = jsonObject.getString("endDate");
        //按照MySQL中的province表将城市编码转化为城市名
        String city_code = jsonObject.getString("city_code");
        data[8] = provinceMap.get(city_code);
        data[9] = jsonObject.getString("companySize");
        data[10] = jsonObject.getString("welfare");
        data[11] = jsonObject.getString("responsibility");
        data[12] = jsonObject.getString("place");
        data[13] = jsonObject.getString("workingExp");

        //一条数据若包含空值，删除
        for (String str : data){
            if (str == null || "".equals(str)){
                return;
            }
        }

        Employment employment = new Employment(data[0],data[1],data[2],data[3],data[4],data[5],data[6],
                data[7],data[8],data[9],data[10],data[11],data[12],data[13]);

        context.write(employment,NullWritable.get());
    }
}
