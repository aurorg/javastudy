package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.FriendGetFilemsg;
import message.Message;
import message.ServerToClientmsg;

import java.io.File;
import java.sql.*;

public class SFriendGetFileHandler extends SimpleChannelInboundHandler<FriendGetFilemsg> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FriendGetFilemsg friendGetFilemsg) throws Exception {
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
                String message=rs.getString("message");
                if(issuccess1==6) {
                    message1 = new ServerToClientmsg(false, "嗯哼？！你已经保存过该文件啦");
                }else if(issuccess1==5 && message.equals("接收文件消息")){
                    //先更新消息表中的信息
                    String sql1 = " update message set issuccess =6  where(senderid=? and receiverid=?)  or (receiverid=? and senderid=?)";
                    ps = conn.prepareStatement(sql1);
                    ps.setInt(1, userid2);
                    ps.setInt(2, friendid2);
                    ps.setInt(3, userid2);
                    ps.setInt(4, friendid2);
                    ps.executeUpdate();

                    message1=new ServerToClientmsg(true,"收到你要保存文件的信息啦！");


                    //这里要不要加下面的
//                    File file=new File();
//                    message1.setFile(file);

                    message1.setMessageType(Message.FriendGetFilemsg);

//                    Channel channel = ChatHandlerMap.getChannel(friendid2);
//                    if(channel==null){
//                        channel.writeAndFlush(new FriendChatmsg());
                }else{
                    message1 = new ServerToClientmsg(false, "好的，拒绝接受文件成功！");
                }

            }


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
