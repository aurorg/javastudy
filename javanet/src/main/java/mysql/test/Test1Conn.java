package mysql.test;

//数据库连接

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test1Conn {
    public static void main(String[] args) {
        Connection con;

        //jdbc驱动
        String driver ="com.mysql.cj.jdbc.Driver";

        //数据库
        String url ="jdbc:mysql://localhost:3306/happy?&useSSL=false&serverTimezone=UTC";

        //用户和密码
        String user ="root";
        String password="szl0905";
        try{
            //注册jdbc驱动
            Class.forName(driver);

            //建立连接
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed()){
                System.out.println("数据库连接成功");
            }
            con.close();
        }catch (ClassNotFoundException e){
            System.out.println("数据库驱动没有安装");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库安装失败");
        }
    }
}
