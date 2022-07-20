package mysql.temp;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class jdbcInser{
//    public void jdbcInser(){
//
//        ResultSet resultSet = null;
//        Statement statement = null;
//
//        Connection connection = null;
//
//        try {
//            connection =connection.getConnection();
//            statement = connection.createStatement();
//
//            String name = "小f";
//            int score = 99;
//            String classs = "初2-4班";
//
//            String sql = "INSERT INTO  student(name,score,class) "
//                    +" values('"+name+"','"+String.valueOf(score)+"','"+classs+"')";
//
//            int result = statement.executeUpdate(sql);
//            //executeUpdate:用来实现INSERT、UPDATE 或 DELETE 语句,返回值表示执行sql语句之后影响到的数据行数
//
//            System.out.println("插入了"+result+"条数据");
//
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }finally {
//
//            JdbcUtils.releaseResc(resultSet, statement, connection);        //释放资源
//        }
//    }
//}