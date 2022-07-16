package mysql.test;

import java.sql.*;

public class Test2MysqlDemo {

    //jdbc驱动，数据库URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/happy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    //数据库的用户名和账号、
    static final String USER = "root";
    static final String PASS ="szl0905";
    public static void main(String[] args) {
        Connection conn =null;
        Statement stmt =null;
        try{
            //注册jdbc驱动
            Class.forName(JDBC_DRIVER);

            //打开连接
            System.out.println("连接数据库.....");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //执行查询
            System.out.println("实例化Statement对象...");
            stmt =conn.createStatement();
            String sql;
            sql = "SELECT owner,name,birth FROM pet";
            ResultSet rs =stmt.executeQuery(sql);

            //展开结果集数据库
            while(rs.next()){
                //通过字段搜索
                String owner =rs.getString("owner");
                String name =rs.getString("name");
                String birth =rs.getString("birth");

                //输出数据
                System.out.print("主人owner: " + owner);
                System.out.print(",宠物名称name: " + name);
                System.out.print(",宠物生日birth: " + birth);
                System.out.println("\n");
            }

            //完成后关闭资源
            rs.close();
            stmt.close();
            conn.close();

        }catch(SQLException se){
            //处理jdbc错误
            se.printStackTrace();
        }catch(Exception e){
            //处理class.forName错误
            e.printStackTrace();
        }finally{
            //关闭资源
            try{
                if(stmt!=null)
                {
                    stmt.close();
                }
            }catch(SQLException se2){

            }//什么都不用做哦

            try{
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye");
    }
}
