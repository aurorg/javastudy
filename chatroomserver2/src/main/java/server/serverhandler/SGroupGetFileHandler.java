package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.GroupGetFilemsg;
import message.Message;
import message.ServerToClientmsg;

import java.io.File;
import java.sql.*;

public class SGroupGetFileHandler extends SimpleChannelInboundHandler<GroupGetFilemsg> {

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
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupGetFilemsg groupGetFilemsg) throws Exception {

        try{
            System.out.println("接收文件打印消息1" + groupGetFilemsg);

            int userid1 = groupGetFilemsg.getUserid();
            int groupid1 = groupGetFilemsg.getGroupid();
            String filename=groupGetFilemsg.getMessage();
            System.out.println("打印文件名称" + filename);

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
            sql="SELECT issuccess,messagetype,message FROM message where groupid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, groupid1);

            rs = ps.executeQuery();

            while(rs.next()){
                int issuccess1 = rs.getInt("issuccess");
                String messagetype=rs.getString("messagetype");
                String msg=rs.getString("message");

                if(issuccess1==14 && messagetype.equals("FILE")  && msg.equals(filename)) {
                    message1 = new ServerToClientmsg(false, "嗯哼？！你已经保存过该群文件啦");
                    ctx.writeAndFlush(message1);
                }else if(issuccess1==13 && messagetype.equals("FILE") && msg.equals(filename)){

                    message1=new ServerToClientmsg(true,"收到你要保存群文件的信息啦！");
                    ctx.writeAndFlush(message1);


                    File file=new File(filename);
                    System.out.println("打印一下路径"+filename);
                    message1.setFile(file);

                    message1.setMessageType(Message.GroupGetFilemsg);
                    ctx.writeAndFlush(message1);

                    //更新消息表中的信息
                    String sql1 = " update message set issuccess =14  where groupid=? and messagetype=?";
                    ps = conn.prepareStatement(sql1);
                    ps.setInt(1, groupid1);
                    ps.setString(2,"FILE");
                    ps.executeUpdate();

                    System.out.println("测试收文件555555555");

        }
    }

            stat.close();
            conn.close();
        }catch(
                SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e)
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
