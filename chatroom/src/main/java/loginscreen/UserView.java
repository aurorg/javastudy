package loginscreen;

import java.sql.*;
import java.util.Scanner;

//用户界面

public class UserView {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/chatroom?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    //数据库用户和密码
    static final String USER = "root";
    static final String PASS = "szl0905";


    //数据的连接对象
    static Connection conn = null;

    //传输器
    static Statement stat = null;

    //sql语句的执行结果
    static ResultSet rs = null;

    //用户输入
    static Scanner input = new Scanner(System.in);

//----------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args){
        /**
         * 1.进行用户登录
         * 先取数据库中找这个用户是否存在，如果存在的话，继续进行下一步操作；
         * 如果这个用户不存在的话，调用用户注册函数，注册一个账号，之后再进行后续的操作。
         */
        //用户登录
        loginview();


    }
//----------------------------------------------------------------------------------------------------------------------

    //用户登录界面
    public static void loginview() {
        System.out.println("*******************************");
        System.out.println("*        欢迎来到用户界面        *");
        System.out.println("*       根据您的需求进行选择      *");
        System.out.println("*         [1]:用户注册          *");
        System.out.println("*         [2]:用户登录          *");
        System.out.println("*         [3]:用户注销          *");
        System.out.println("*******************************");

        int n = input.nextInt();
        switch (n) {
            case 1:
                enroll();
                break;
            case 2:
                login();
                break;
            case 3:
                logout();
                break;
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    //用户注册
    public static void enroll(){
        System.out.println("请输入您的手机号码进行注册（6位）：");
        int pn1 =input.nextInt();

        //去数据库中查找该手机号码注册过账号没有【规定，一个账号只能注册一个电话号码】
        //注册过了：选择新的手机号进行注册
        //没有注册过：继续进行登录
        //检索数据库看这个用户的数据是否在数据库中
          try {

              //注册JDBC驱动
              Class.forName(JDBC_DRIVER);

              //获得数据库链接
             //System.out.println("连接数据库.....");
             conn = DriverManager.getConnection(DB_URL, USER, PASS);

             //执行查询
              //System.out.println("实例化Statement对象...");

              //创建传输器
             stat = conn.createStatement(); //createStatement()：创建向数据库发送sql的statement对象。


              String sql;
              sql = "SELECT phonenumber FROM usermsg";

              //传输sql并且返回结果
              ResultSet rs = stat.executeQuery(sql); //executeQuery(String sql) ：用于向数据发送查询语句

              //展开结果集数据库
              //next()会将光标向下移动一行，
              //并返回当前行是否有效，如果遍历完成整个表，则会返回false

              boolean isexit =false; //临时变量，判断该电话号码是否存在

              while (rs.next()) {

                  // 通过字段检索
                  int phonenumber =rs.getInt("phonenumber");


                  if(pn1==phonenumber){
                       isexit =true;
                  }

              }

              //判断之后进行后续选择
              if(isexit){
                  System.out.println("您的电话号码已被使用，请选择新的电话号码进行注册：");
                  enroll();
              }else{
                  System.out.println("注册成功");

                  System.out.println("请输入您的账号昵称：");
                  String name1 =input.next();

                  System.out.println("请输入您的账号密码：");
                  String psw1 =input.next();


                  //注册成功之后将信息加入到数据库
                  adduser();
                  //返回一个账号（也就是id号）

                  login();
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



    }
//----------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------
    //将账号记录到数据库
    public static void adduser(){
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stat = conn.createStatement();

            //利用传输器传输数据
            int count = stat.executeUpdate("insert into usermsg(username,userpassword,phonenumber) values('aoliao','aoliao123','654321')");
            //executeUpdate(String sql)：用于向数据库发送insert、update或delete语句

            //返回相应的行数
            if(count > 0){
                System.out.println("添加成功，受到影响的行数为："+count);
                rtid();
            }else{
                System.out.println("添加失败");
            }

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            //6.关闭资源
            //后创建的先关闭
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//----------------------------------------------------------------------------------------------------------------------


    //查询id号给用户
    public static void rtid(){
        try {

            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //获得数据库链接
            //System.out.println("连接数据库.....");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //执行查询
            //System.out.println("实例化Statement对象...");

            //创建传输器
            stat = conn.createStatement(); //createStatement()：创建向数据库发送sql的statement对象。


            String sql;
            sql = "SELECT userid FROM usermsg where phonenumber = '654321'";

            //传输sql并且返回结果
            ResultSet rs = stat.executeQuery(sql); //executeQuery(String sql) ：用于向数据发送查询语句

            //展开结果集数据库
            //next()会将光标向下移动一行，
            //并返回当前行是否有效，如果遍历完成整个表，则会返回false

            while (rs.next()) {

                // 通过字段检索
                int userid =rs.getInt("userid");
                System.out.println("您的账号是：" + userid);

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

    }
//----------------------------------------------------------------------------------------------------------------------
    //用户登录
    public static void login(){
        System.out.println("请输入您的账号【id】:");
        int userid1 =input.nextInt();

        System.out.println("请输入您的密码：");
        String psw1 =input.next();

        System.out.println("请再次确认密码：");
        String psw2 =input.next();

        


    }

    //用户注销
    public static void logout(){

    }


}
