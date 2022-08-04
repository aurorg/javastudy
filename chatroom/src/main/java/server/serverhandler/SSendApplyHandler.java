package server.serverhandler;

import common.ChatHandlerMap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.FriendChatmsg;
import message.SendApplyMessage;
import message.ServerToClientmsg;

import java.sql.*;

public class SSendApplyHandler extends SimpleChannelInboundHandler<SendApplyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendApplyMessage sendApplyMessage) throws Exception {

        // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
         final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
         final String DB_URL = "jdbc:mysql://localhost:3306/chatroom?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        //数据库用户和密码
        final String USER = "root";
        final String PASS = "szl0905";


        //数据的连接对象
          Connection conn = null;

        //传输器
          Statement stat = null;

        //sql语句的执行结果
          ResultSet rs = null;

        //记录语句的输入
          PreparedStatement ps = null;

        //用户输入
//    static Scanner input = new Scanner(System.in);
        try{
            //打印接受的消息
            System.out.println("打印消息" +sendApplyMessage );

            //接收消息的部分
            int userid2=sendApplyMessage.getUserid();
            int friendid2=sendApplyMessage.getFriendid();
            String message=sendApplyMessage.getMessage();

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
            sql="SELECT userid,friendid,isfriend FROM friendlist where(userid=? and friendid=?)  or (friendid=? and userid=?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid2);
            ps.setInt(2, friendid2);
            ps.setInt(3, userid2);
            ps.setInt(4, friendid2);
            rs = ps.executeQuery();

//            while(rs.next()){
//                int isfriend1 = rs.getInt("isfriend");
//                if(isfriend1==1) {
//                    message1 = new ServerToClientmsg(false, "嗯哼？！你俩已经是好友了");
//                }else{
                    String sql1 = "insert into message(senderid,receiverid,message,issuccess,messagetype,chattype) values(?,?,?,?,?,?) ";
                    ps = conn.prepareStatement(sql1);
                    ps.setInt(1, userid2);
                    ps.setInt(2, friendid2);
                    ps.setString(3,"请求加好友" );
                    ps.setInt(4, 3);
                    ps.setString(5,"APPLY");
                    ps.setString(6,"FRIEND");
                    ps.executeUpdate();

//                    //将好友信息存到表中
//                    String sql2="insert into friendlist(userid,friendid,isfriend,isshield,state) values(?,?,?,?,?) ";
//                    ps = conn.prepareStatement(sql2);
//                    ps.setInt(1, userid2);
//                    ps.setInt(2, friendid2);
//                    ps.setInt(3,1 );
//                    ps.setInt(4, 1);
//                    ps.setInt(5,2);
//                    ps.executeUpdate();

//                    //更新之前的数据
//                    String sql3 = " update friendlist set isfriend =1  where ((userid=? and friendid=?)  or (friendid=?and userid=?) )";
//                    ps = conn.prepareStatement(sql3);
//                    ps.setInt(1, userid2);
//                    ps.setInt(2, friendid2);
//                    ps.setInt(3, userid2);
//                    ps.setInt(4, friendid2);
//                    ps.executeUpdate();


                    message1=new ServerToClientmsg(true,"可以添加!");

                    Channel channel = ChatHandlerMap.getChannel(friendid2);
                    if(channel!=null){
                        channel.writeAndFlush(new FriendChatmsg());
                    }

           // }

//            String sql1 = "insert into message(senderid,receiverid,message,issuccess,messagetype,chattype) values(?,?,?,?,?,?) ";
//            ps = conn.prepareStatement(sql1);
//            ps.setInt(1, userid2);
//            ps.setInt(2, friendid2);
//            ps.setString(3,"请求加好友" );
//            ps.setInt(4, 2);
//            ps.setString(5,"APPLY");
//            ps.setString(6,"FRIEND");
//            int row =ps.executeUpdate();
//            if(row==1){
//                message1=new ServerToClientmsg(true,"添加成功");
//
//                Channel channel = ChatHandlerMap.getChannel(friendid2);
//                if(channel==null){
//                    channel.writeAndFlush(new FriendChatmsg());
//
//                }
//            }else{
//                message1 =new ServerToClientmsg(false,"申请失败啦");
//

           ctx.writeAndFlush(message1);
            stat.close();
            conn.close();
        }catch(
                SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch(
                Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally
        {
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
