package mysql.temp;

import java.sql.*;

public class JDBC {
    //test为数据库名称
    // MySQL 8.0 以下版本选择
    //static final String JdbcDriver = "com.mysql.jdbc.Driver";
    //static final String Url = "jdbc:mysql://localhost:3306/test";

    // MySQL 8.0 以上版本选择
    static final String JdbcDriver = "com.mysql.cj.jdbc.Driver";
    static final String Url = "jdbc:mysql://localhost:3306/happy?useSSL=false&serverTimezone=UTC";


    //输入连接数据库的用户名与密码
    static final String User = "root";//输入你的数据库库名
    static final String PassWord = "szl0905";//输入你的数据库连接密码

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JdbcDriver);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(Url,User,PassWord);

            // 执行查询
            System.out.println("输入sql语句后并执行...");
            stmt = conn.createStatement();
            String sql;

            String owner = "33333";
            String name = "zouge";
            String birth = "2022-07-20";
            sql =  "INSERT INTO student(owner,name,birth) "
                  +" values('"+owner+"','"+name+"','"+birth+"')";// 这里填写需要的sql语句
            //执行sql语句
            ResultSet rs = stmt.executeQuery(sql);

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("\n执行成功！");
    }
}

