package spi;

import java.sql.Connection;
import java.sql.SQLException;

public class TestMySql {
    public static void main(String[] args) {
        // 加载Class到AppClassLoader（系统类加载器），然后注册驱动类
// Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3306/test";
// 通过java库获取数据库连接
        try {
            Connection conn = java.sql.DriverManager.getConnection(url, "root", "123456");
            System.out.println(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
