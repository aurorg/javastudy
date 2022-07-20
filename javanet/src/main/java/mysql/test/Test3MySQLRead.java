package mysql.test;

import java.sql.*;

public class Test3MySQLRead {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/happy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    //数据库用户和密码
    static final String USER ="root";
    static final String PASS ="szl0905";

    public static void main(String[] args) {
         Connection conn =null;
         Statement stmt =null;

         try{

             //注册JDBC驱动
             Class.forName(JDBC_DRIVER);

             //获得数据库链接
             System.out.println("连接数据库.....");
             conn =DriverManager.getConnection(DB_URL,USER,PASS);

             //执行查询
             System.out.println("实例化Statement对象...");

             //创建传输器
             stmt =conn.createStatement();
             String sql;
             sql = "SELECT owner,name,birth FROM pet";

             //传输sql并且返回结果
             ResultSet rs = stmt.executeQuery(sql);

             //展开结果集数据库
             //next()会将光标向下移动一行，
             //并返回当前行是否有效，如果遍历完成整个表，则会返回false
             while(rs.next()){

                 // 通过字段检索
                 String owner  = rs.getString("owner");
                 String name = rs.getString("name");
                 String birth = rs.getString("birth");

                 // 输出数据
                 System.out.print("主人owner: " + owner);
                 System.out.print(", 宠物名称name: " + name);
                 System.out.print(", 宠物生日birth: " + birth);
                 System.out.print("\n");
             }
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
             }// 什么都不做
             try{
                 if(conn!=null) conn.close();
             }catch(SQLException se){
                 se.printStackTrace();
             }
         }
        System.out.println("Goodbye!");
    }
}