package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.DeleteFriendmsg;
import message.ServerToClientmsg;

import java.sql.*;

public class SDeleteFriendHandler extends SimpleChannelInboundHandler<DeleteFriendmsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DeleteFriendmsg deleteFriendmsg) throws Exception {
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
            System.out.println("打印消息" +deleteFriendmsg);

            //接收消息的部分
            int userid2=deleteFriendmsg.getUserid();
            int friendid2= deleteFriendmsg.getFriendid();

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
            sql="DELETE FROM friendlist where(userid=? and friendid=?)  or (friendid=? and userid=?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid2);
            ps.setInt(2, friendid2);
            ps.setInt(3, userid2);
            ps.setInt(4, friendid2);
            int count = ps.executeUpdate();
            if(count>0){
                message1=new ServerToClientmsg(true,"删除好友成功。");
            }else{
                message1=new ServerToClientmsg(false,"没有这个朋友问什么要删除呢？？！");
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
