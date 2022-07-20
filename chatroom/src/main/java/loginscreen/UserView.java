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

    public static void main(String[] args){
        /**
         * 1.进行用户登录
         * 先取数据库中找这个用户是否存在，如果存在的话，继续进行下一步操作；
         * 如果这个用户不存在的话，调用用户注册函数，注册一个账号，之后再进行后续的操作。
         */
        //用户登录
        loginview();


    }

    //用户登录界面
    public static void loginview() {
        System.out.println("*******************************");
        System.out.println("*        欢迎来到用户界面        *");
        System.out.println("*       根据您的需求进行选择      *");
        System.out.println("*         [1]:用户登录          *");
        System.out.println("*         [2]:用户注册          *");
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

    //用户登录函数
    public static void enroll(){

        System.out.println("请输入您的账号名称:");
        String name=input.next();

        System.out.println("请输入您的密码:");
        String password1 =input.next();


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
            sql = "SELECT username,userpassword FROM usermsg";

            //传输sql并且返回结果
            ResultSet rs = stat.executeQuery(sql); //executeQuery(String sql) ：用于向数据发送查询语句

            //展开结果集数据库
            //next()会将光标向下移动一行，
            //并返回当前行是否有效，如果遍历完成整个表，则会返回false

            boolean isexit =false; //临时变量，判断该用户是否存在

            while (rs.next()) {

                // 通过字段检索
                String username = rs.getString("username");
                String userpassword=rs.getString("userpassword");

                if(username.equals(name) ==true && userpassword.equals(password1)==true){
                     isexit =true;
                }
//                else {
//                    System.out.println("抱歉，您的用户不存在，您可以选择注册用户");
//                }
            }

            //判断之后进行后续选择
            if(isexit){
                System.out.println("您的账户存在，您可以选择后续操作");
                login();
            }else{
                System.out.println("抱歉，您的账户不存在，您可以选择注册账户");
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



    //用户注册函数
    public static void login(){


    }

    //用户注销
    public static void logout(){

    }



}
