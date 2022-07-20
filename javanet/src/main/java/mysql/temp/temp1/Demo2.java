package mysql.temp.temp1;


//
//import java.sql.*;
//
//
//public class Demo2  extends Demo1 {
//
//    //数据的连接对象
//    Connection conn = null;
//
//    //传输器
//    Statement stat = null;
//
//    //sql语句的执行结果
//    ResultSet rs = null;
//
//
//    //读取数据
//    public void read(){
//
////        //数据库的连接对象
////        Connection conn = null;
////
////        //创建传输器
////        Statement stmt = null;
//
//
//        try {
//
//            //注册JDBC驱动
//            Class.forName(JDBC_DRIVER);
//
//            //获得数据库链接
//            System.out.println("连接数据库.....");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            //执行查询
//            System.out.println("实例化Statement对象...");
//
//            //创建传输器
//            stat = conn.createStatement();
//            String sql;
//            sql = "SELECT owner,name,birth FROM pet";
//
//            //传输sql并且返回结果
//            ResultSet rs = stat.executeQuery(sql);
//
//            //展开结果集数据库
//            //next()会将光标向下移动一行，
//            //并返回当前行是否有效，如果遍历完成整个表，则会返回false
//            while (rs.next()) {
//
//                // 通过字段检索
//                String owner = rs.getString("owner");
//                String name = rs.getString("name");
//                String birth = rs.getString("birth");
//
//                // 输出数据
//                System.out.print("主人owner: " + owner);
//                System.out.print(", 宠物名称name: " + name);
//                System.out.print(", 宠物生日birth: " + birth);
//                System.out.print("\n");
//            }
//            // 完成后关闭
//            rs.close();
//            stat.close();
//            conn.close();
//
//        } catch (SQLException se) {
//            // 处理 JDBC 错误
//            se.printStackTrace();
//        } catch (Exception e) {
//            // 处理 Class.forName 错误
//            e.printStackTrace();
//        } finally {
//            // 关闭资源
//            try {
//                if (stat != null) stat.close();
//            } catch (SQLException se2) {
//            }// 什么都不做
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        System.out.println("Goodbye!");
//    }
//
//
//    //删除
//    public  void del()  throws SQLException,Exception {
//        try{
//            //初始化数据库的连接
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            //创建向数据库发送sql的statement对象
//            stat = conn.createStatement();
//
//            //删除的mysql语句，然后进行删除
//            //count用来返回相应的行数
//            int count = stat.executeUpdate("delete from pet where name= 'mary'");
//            if(count>0){
//                System.out.println("操作成功，受到影响的行数为："+count);
//            }else{
//                System.out.println("操作失败");
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }finally{
//
//            //关闭资源
//             rs.close();
//             stat.close();
//             conn.close();
//        }
//    }
//
//
//    //添加一条新的数据
//    public  void add() throws SQLException,Exception{
//        try{
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            stat = conn.createStatement();
//            //4.利用传输器传输数据，并返响应行数。
//            int count = stat.executeUpdate("insert into pet values('mimi','xiaozhang','cat','f','1111-11-11',null)");
//
//            if(count > 0){
//                System.out.println("操作成功，受到影响的行数为："+count);
//            }else{
//                System.out.println("操作失败");
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }finally{
//            //6.关闭资源
//            //后创建的先关闭
//            rs.close();
//            stat.close();
//            conn.close();
//        }
//
//    }
//
//
//  //更新数据，也就是修改
//    public  void update() throws SQLException,Exception{
//        try{
//
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            stat = conn.createStatement();
//
//            //4.利用传输器传输数据，并返响应行数。
//            int count = stat.executeUpdate("update pet set owner = 'nana' where name = 'aoliao'");
//
//            if(count > 0){
//                System.out.println("操作成功，受到影响的行数为："+count);
//            }else{
//                System.out.println("操作失败");
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }finally{
//            //6.关闭资源
//            //后创建的先关闭
//            rs.close();
//            stat.close();
//            conn.close();
//        }
//
//    }
//
//}
//
