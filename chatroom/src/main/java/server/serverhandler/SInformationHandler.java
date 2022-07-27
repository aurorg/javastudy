package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.Informationmsg;
import message.Message;
import message.ServerToClientmsg;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SInformationHandler extends SimpleChannelInboundHandler<Informationmsg> {

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

    //记录语句的输入
    static PreparedStatement ps =null;

    //用户输入
//    static Scanner input = new Scanner(System.in);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Informationmsg informationmsg) throws Exception {

        try {

            //每次先打印一下下，看消息发过来没有！！！！！！
            System.out.println("打印消息"+informationmsg);


            //接受消息的部分
           int userid2 = informationmsg.getUserid();
           int friendid2 =informationmsg.getFriendid();

            ServerToClientmsg message1 = null;

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
            sql = "SELECT senderid,receiverid,message FROM message where (senderid=? and receiverid=?) or (receiverid=? and senderid=?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid2);
            ps.setInt(2, friendid2);
            ps.setInt(3, userid2);
            ps.setInt(4, friendid2);
            rs = ps.executeQuery();

            //传输sql并且返回结果
            //ResultSet rs = stat.executeQuery(sql); //executeQuery(String sql) ：用于向数据发送查询语句

            //展开结果集数据库
            //next()会将光标向下移动一行，
            //并返回当前行是否有效，如果遍历完成整个表，则会返回false

            //boolean isexit =false; //临时变量，判断该电话号码是否存在

            while (rs.next()) {

                // 通过字段检索
                 int sendid1 =rs.getInt("senderid");
                 int receiver1=rs.getInt("receiverid");
                 String message=rs.getString("message");

                 //输出数据
                System.out.println("发送者sendid: " + sendid1 + " ,接受者receiverid: " + receiver1 + " ,发的消息是message: " + message);
//                System.out.print(" ,接受者receiverid: " + receiver1);
//                System.out.print(" ,发的消息是message: " + message);
              //  System.out.print("\n");

                //明天改，将消息存到list中
                List<String> messagelist =new ArrayList<>();
                messagelist.add("发送者sendid: " + sendid1 + " ,接受者receiverid: " + receiver1 + " ,发的消息是message: " + message);

                //message1=new SInformationHandler(sendid1,receiver1,message);

            }
            //判断之后进行后续选择

            message1.setMessageType(Message.Informationmsg);
            ctx.writeAndFlush(message1);

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
}
