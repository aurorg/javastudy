package mysql.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Test4MySQLWrite {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        Connection connection =null;
        PreparedStatement ps =null;


        try{
            //1.register
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2.getConnection
            String url="jdbc:mysql://localhost:3306/happy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            Properties info = new Properties();
            info.put("user","root");
            info.put("password","szl0905");
            connection= DriverManager.getConnection(url,info);

            String sql ="insert into pet(owner,name,birth) values(?,?,?)";
            ps =connection.prepareStatement(sql);

            System.out.println("请输入1：");
            String s1=input.next();

            ps.setString(1,s1);

            System.out.println("请输入2：");
            String s2=input.next();
            ps.setString(2,s2);

            System.out.println("请输入3：");
            String s3=input.next();
            ps.setString(3,s3);

            int resultSet =ps.executeUpdate();
            if(resultSet >0){
                System.out.println("Success");
            }else{
                System.out.println("Failure");
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
