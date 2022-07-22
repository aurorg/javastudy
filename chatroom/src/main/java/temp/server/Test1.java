package temp.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.sql.*;

public class Test1 {
}
//package server.serverhandler;
//
//        import io.netty.buffer.ByteBuf;
//        import io.netty.channel.ChannelHandlerContext;
//        import io.netty.channel.ChannelInboundHandlerAdapter;
//        import io.netty.channel.SimpleChannelInboundHandler;
//        import io.netty.util.CharsetUtil;
//
//        import java.sql.*;
//        import java.util.Scanner;
//
//public class SLoginViewHandle extends ChannelInboundHandlerAdapter {
//    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/chatroom?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//
//    //数据库用户和密码
//    static final String USER = "root";
//    static final String PASS = "szl0905";
//
//
//    //数据的连接对象
//    static Connection conn = null;
//
//    //传输器
//    static Statement stat = null;
//
//    //sql语句的执行结果
//    static ResultSet rs = null;
//
//    //记录语句的输入
//    static PreparedStatement ps =null;
//
//    //用户输入
////    static Scanner input = new Scanner(System.in);
//
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//        ByteBuf buf=(ByteBuf) msg;
//        System.out.println("客户端发送的消息是：" + buf.toString(CharsetUtil.UTF_8));
//        int pn1=buf.readInt();
//        System.out.println(pn1);
//        //enroll1(pn1);
//
//    }
//    public void enroll1(ChannelHandlerContext ctx){
//        try {
//
//            //注册JDBC驱动
//            Class.forName(JDBC_DRIVER);
//
//            //获得数据库链接
//            //System.out.println("连接数据库.....");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            //执行查询
//            //System.out.println("实例化Statement对象...");
//
//            //创建传输器
//            stat = conn.createStatement(); //createStatement()：创建向数据库发送sql的statement对象。
//
//
//            String sql;
//            sql = "SELECT phonenumber FROM usermsg";
//
//            //传输sql并且返回结果
//            ResultSet rs = stat.executeQuery(sql); //executeQuery(String sql) ：用于向数据发送查询语句
//
//            //展开结果集数据库
//            //next()会将光标向下移动一行，
//            //并返回当前行是否有效，如果遍历完成整个表，则会返回false
//
//            boolean isexit =false; //临时变量，判断该电话号码是否存在
//
//            while (rs.next()) {
//
//                // 通过字段检索
//                int phonenumber =rs.getInt("phonenumber");
//
//
//                if(pn1==phonenumber){
//                    isexit =true;
//                }
//
//            }
//
//            //判断之后进行后续选择
//            if(isexit){
//                System.out.println("您的电话号码已被使用，请选择新的电话号码进行注册：");
//                enroll();
//            }else{
//                System.out.println("注册成功");
//
//                System.out.println("请输入您的账号昵称：");
//                String name1 =input.next();
//
//                System.out.println("请输入您的账号密码：");
//                String psw1 =input.next();
//
//
//                //注册成功之后将信息加入到数据库
//                adduser();
//                //返回一个账号（也就是id号）
//
//                login();
//            }
//
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
//
//        }
//    }
//}
