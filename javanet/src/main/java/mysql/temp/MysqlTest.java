package mysql.temp;

import java.sql.*;
import java.util.Properties;



public class MysqlTest {
    //插入insert单个数据

    public static void main(String[] args){
        Connection connection=null;
        PreparedStatement ps=null;

        try {
            //1.register
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2.getConnection
            String url="jdbc:mysql://localhost:3306/happy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            Properties info=new Properties();
            info.put("user", "root");
            info.put("password", "szl0905");
            connection=DriverManager.getConnection(url, info);

            //3.create Statement
            String sql="insert into pet (owner,name,birth) values(?,?,?)";
            ps=connection.prepareStatement(sql);
            ps.setString(1, "3333");
            ps.setString(2, "mary");
            ps.setString(3, "2022-07-20");

            //4.excuteUpdate
            int resultSet=ps.executeUpdate();
            if(resultSet>0){
                //如果插入成功，则打印success
                System.out.println("Success");
            }else{
                //如果插入失败，则打印Failure
                System.out.println("Failure");
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            //5.关闭资源
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
