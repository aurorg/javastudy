package mysql.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Test4MySQLWrite {
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

            ps.setString(1,"444444");
            ps.setString(2,"lalala");
            ps.setString(3,"2004-02-02");

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
