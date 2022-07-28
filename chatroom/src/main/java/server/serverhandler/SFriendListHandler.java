package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.FriendListmsg;
import message.Message;
import message.ServerToClientmsg;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SFriendListHandler extends SimpleChannelInboundHandler<FriendListmsg> {
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
    protected void channelRead0(ChannelHandlerContext ctx, FriendListmsg friendListmsg) throws Exception {
        try {

            //每次先打印一下下，看消息发过来没有！！！！！！
            System.out.println("打印消息是"+friendListmsg);


            //接受消息的部分
            int userid2 = friendListmsg.getUserid();

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
            sql = "SELECT userid,friendid,isfriend,isshield FROM friendlist where userid=?  or friendid=?  )";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid2);
            ps.setInt(2, userid2);
            int count = ps.executeUpdate();
            if(count>0){
                message1=new ServerToClientmsg(true,"查询好友列表成功。");
            }else{
                message1=new ServerToClientmsg(false,"你还没有添加过好友哦？？！");
            }

            //传输sql并且返回结果
            //ResultSet rs = stat.executeQuery(sql); //executeQuery(String sql) ：用于向数据发送查询语句

            //展开结果集数据库
            //next()会将光标向下移动一行，
            //并返回当前行是否有效，如果遍历完成整个表，则会返回false

            //boolean isexit =false; //临时变量，判断该电话号码是否存在
            List<String> friendlist =new ArrayList<>();
            while (rs.next()) {

                // 通过字段检索
                int userid1 =rs.getInt("userid");
                int friendid1=rs.getInt("friendid1");
                int isfriend1=rs.getInt("isfriend1");
                int isshield1=rs.getInt("isshield1");

                //输出数据
                System.out.println("你的账号userid: " + userid1 + " ,好友的账号friendid: " + friendid1 +
                        " ,是否好友isfriend: " + isfriend1  +  " ,是否屏蔽isshield:"+isshield1);
//
                //  System.out.print("\n");


                //List<String> messagelist =new ArrayList<>();
                friendlist.add("你的账号userid: " + userid1 + " ,好友的账号friendid: " + friendid1 +
                        " ,是否好友isfriend: " + isfriend1  +  " ,是否屏蔽isshield:"+isshield1);

                //message1=new SInformationHandler(sendid1,receiver1,message);

            }
            //判断之后进行后续选择
            // System.out.println("11111111");
             message1 = new ServerToClientmsg(true,"好友列表如下：");
            // System.out.println("111111112222");
            message1.setFriendmsglist(friendlist);
            // System.out.println("11111111333333333");
            message1.setMessageType(Message.FriendListmsg);
            // System.out.println("4444444444444444");
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
