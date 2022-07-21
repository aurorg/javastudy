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

    //用户注册
    public static void enroll(){

    }

    //用户登录
    public static void login(){

    }

    //用户注销
    public static void logout(){

    }


}
