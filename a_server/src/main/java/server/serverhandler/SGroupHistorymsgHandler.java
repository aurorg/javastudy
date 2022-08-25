package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.GroupHistoryMessage;
import message.Message;
import message.ServerToClientmsg;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SGroupHistorymsgHandler extends SimpleChannelInboundHandler<GroupHistoryMessage> {

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

    @Override
    protected void channelRead0(ChannelHandlerContext  ctx, GroupHistoryMessage groupHistoryMessage) throws Exception {
        try {

            //每次先打印一下下，看消息发过来没有！！！！！！
            System.out.println("打印消息"+groupHistoryMessage);


            //接受消息的部分
            int userid1 =groupHistoryMessage.getUserid();
            int groupid1= groupHistoryMessage.getGroupid();

            //ServerToClientmsg message1 = null;

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
            sql = "SELECT groupid,senderid,receiverid,message,messagetype,chattype FROM message where groupid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, groupid1);

            rs = ps.executeQuery();

            //传输sql并且返回结果
            //ResultSet rs = stat.executeQuery(sql); //executeQuery(String sql) ：用于向数据发送查询语句

            //展开结果集数据库
            //next()会将光标向下移动一行，
            //并返回当前行是否有效，如果遍历完成整个表，则会返回false

            //boolean isexit =false; //临时变量，判断该电话号码是否存在
            List<String> grouphistorymsg =new ArrayList<>();
            while (rs.next()) {

                // 通过字段检索
                int sendid1 =rs.getInt("senderid");
                int receiver1=rs.getInt("receiverid");
                String message=rs.getString("message");
                String chattype=rs.getString("chattype");
                String messagetype=rs.getString("messagetype");

                //输出数据
                System.out.println("发送者sendid: " + sendid1 + " ,接受者receiverid: " + receiver1 +
                        " ,发的消息是message: " + message  +  " ,聊天类型chattype:"+chattype + " ,消息类型message:" + messagetype);
//                System.out.print(" ,接受者receiverid: " + receiver1);
//                System.out.print(" ,发的消息是message: " + message);
                //  System.out.print("\n");


                //List<String> messagelist =new ArrayList<>();
                grouphistorymsg.add("发送者sendid: " + sendid1 + " ,接受者receiverid: " + receiver1 +
                        " ,发的消息是message: " + message  +  " ,聊天类型chattype:"+chattype + " ,消息类型message:" + messagetype);

                //message1=new SInformationHandler(sendid1,receiver1,message);

            }
            //判断之后进行后续选择
            // System.out.println("11111111");
            ServerToClientmsg message1 = new ServerToClientmsg(true,"群消息如下：");
            // System.out.println("111111112222");
            message1.setGrouphistorymsg(grouphistorymsg);
            // System.out.println("11111111333333333");
            message1.setMessageType(Message.GroupHistorymsg);
            // System.out.println("4444444444444444");
            ctx.writeAndFlush(message1);

            String sql1;
            sql1 = "update message set issuccess =12 where groupid=? and messagetype=? and chattype=?";
            ps=conn.prepareStatement(sql1);
            ps.setInt(1,groupid1);
            ps.setString(2,"TEXT");
            ps.setString(3,"GROUP");

            ps.executeUpdate();
            System.out.println("因为你浏览历史消息的时候将所有消息看过了，所以将你的未读消息改为已读消息");

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
