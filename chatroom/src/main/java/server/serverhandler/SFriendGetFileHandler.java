package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.FriendGetFilemsg;
import message.ServerToClientmsg;

import java.sql.*;

public class SFriendGetFileHandler extends SimpleChannelInboundHandler<FriendGetFilemsg> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FriendGetFilemsg friendGetFilemsg) throws Exception {
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

        try{
            //打印接受的消息
            System.out.println("打印消息" + friendGetFilemsg);

            //接收消息的部分
            int userid2=friendGetFilemsg.getUserid();
            int friendid2=friendGetFilemsg.getFriendid();
            String message2=friendGetFilemsg.getMessage();

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
            sql="SELECT issuccess FROM message where(senderid=? and receiverid=?)  or (receiverid=? and senderid=?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid2);
            ps.setInt(2, friendid2);
            ps.setInt(3, userid2);
            ps.setInt(4, friendid2);
            rs = ps.executeQuery();

            while(rs.next()){
                int issuccess1 = rs.getInt("issuccess");
                if(issuccess1==4) {
                    message1 = new ServerToClientmsg(false, "嗯哼？！你已经通过好友申请了");
                }else if(issuccess1==3){
                    //先更新消息表中的信息
                    String sql1 = " update message set issuccess =4  where(senderid=? and receiverid=?)  or (receiverid=? and senderid=?)";
                    ps = conn.prepareStatement(sql1);
                    ps.setInt(1, userid2);
                    ps.setInt(2, friendid2);
                    ps.setInt(3, userid2);
                    ps.setInt(4, friendid2);
                    ps.executeUpdate();

                    //将好友信息添加到friendlist中
                    String sql2 = "insert into friendlist(userid,friendid,isfriend,isshield) value(?,?,?,?)  ";
                    ps = conn.prepareStatement(sql2);
                    ps.setInt(1, userid2);
                    ps.setInt(2, friendid2);
                    ps.setInt(3, 1);
                    ps.setInt(4, 1);
                    ps.executeUpdate();


                    message1=new ServerToClientmsg(true,"申请已经通过,已经添加到您的好友列表中");

//                    Channel channel = ChatHandlerMap.getChannel(friendid2);
//                    if(channel==null){
//                        channel.writeAndFlush(new FriendChatmsg());
                }

            }

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
