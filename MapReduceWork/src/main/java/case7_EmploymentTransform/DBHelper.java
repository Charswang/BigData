package case7_EmploymentTransform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/mydb?userUnicode=true&characterEncoding=UTF-8";
    private static final String username = "root";
    private static final String password = "123456";
    private static Connection conn = null;

    //静态代码块
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //静态方法
    public static Connection getConnection(){
        if (conn == null){
            try {
                conn = DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }
        return conn;
    }
}
