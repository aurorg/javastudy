package mysql.temp.temp1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class Demo0 {
    public static void main(String[] args) throws SQLException {
//		注册数据库驱动
        DriverManager.registerDriver(new Driver());
//		获取数据库连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/happy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","szl0905");
//		创建传输器
        Statement stat = conn.createStatement();
//		传输sql并返回结果
        ResultSet rs = stat.executeQuery("select * from pet");
//		遍历结果
        //next()会将光标向下移动一行，
        //并返回当前行是否有效，如果遍历完成整个表，则会返回false
        while(rs.next()){
            String owner= rs.getString("owner");
            String name = rs.getString("name");
            System.out.print("owner" + owner);
            System.out.print(",  name"  + name);
            System.out.print("\n");

        }
//		关闭资源
        //后创建的先关闭
        rs.close();
        stat.close();
        conn.close();

    }

}
