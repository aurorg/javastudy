package mysql.temp.temp1;

import java.sql.*;


public class Demo1 {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/happy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    //数据库用户和密码
    static final String USER = "root";
    static final String PASS = "szl0905";


    //数据的连接对象
    static Connection conn = null;

    //传输器
    static Statement stat = null;

    //sql语句的执行结果
    static ResultSet rs = null;


    public static void main(String[] args)  {

        //1、测试read函数，读取数据库中的内容
        read();
//          del();

        //2、测试del函数，删除数据库中的数据
//        try {
//            del();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //3、测试添加函数
//        try {
//            add();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //4、测试修改函数
//        try {
//            update();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }


    //读取数据
    public static void read(){

//        //数据库的连接对象
//        Connection conn = null;
//
//        //创建传输器
//        Statement stmt = null;


        try {

            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //获得数据库链接
            System.out.println("连接数据库.....");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //执行查询
            System.out.println("实例化Statement对象...");

            //创建传输器
            stat = conn.createStatement(); //createStatement()：创建向数据库发送sql的statement对象。


            String sql;
            sql = "SELECT owner,name,birth FROM pet";

            //传输sql并且返回结果
            ResultSet rs = stat.executeQuery(sql); //executeQuery(String sql) ：用于向数据发送查询语句

            //展开结果集数据库
            //next()会将光标向下移动一行，
            //并返回当前行是否有效，如果遍历完成整个表，则会返回false
            while (rs.next()) {

                // 通过字段检索
                String owner = rs.getString("owner");
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
            stat.close();
            conn.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stat != null) stat.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }


    //删除
    public  static void del(){
        try{
            //初始化数据库的连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //创建向数据库发送sql的statement对象
            stat = conn.createStatement();

            //删除的mysql语句，然后进行删除
            //count用来返回相应的行数
            int count = stat.executeUpdate("delete from pet where name= 'lalala'");
            //executeUpdate(String sql)：用于向数据库发送insert、update或delete语句

            if(count>0){
                System.out.println("删除成功，受到影响的行数为："+count);
            }else{
                System.out.println("删除失败");
            }

            //关闭资源
            //rs.close();
            stat.close();
            conn.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源

            try {
                if (stat != null) stat.close();
            } catch (SQLException se2) {
            }// 什么都不做

            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }




    //添加一条新的数据
    public  static void add() throws SQLException,Exception{
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stat = conn.createStatement();

            //利用传输器传输数据
            int count = stat.executeUpdate("insert into pet values('mimi1','xiaozhang','cat','f','1111-11-11',null)");
            //executeUpdate(String sql)：用于向数据库发送insert、update或delete语句

            //返回相应的行数
            if(count > 0){
                System.out.println("添加成功，受到影响的行数为："+count);
            }else{
                System.out.println("添加失败");
            }

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            //6.关闭资源
            //后创建的先关闭
            //rs.close();
            stat.close();
            conn.close();
        }

    }


    //更新数据，也就是修改
    public static void update() throws SQLException,Exception{
        try{

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stat = conn.createStatement();

            //4.利用传输器传输数据，并返响应行数。
            int count = stat.executeUpdate("update pet set owner = 'nana' where name = 'aoliao'");
            //executeUpdate(String sql)：用于向数据库发送insert、update或delete语句

            if(count > 0){
                System.out.println("修改成功，受到影响的行数为："+count);
            }else{
                System.out.println("修改失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            //6.关闭资源
            //后创建的先关闭
            //rs.close();
            stat.close();
            conn.close();
        }

    }

}
