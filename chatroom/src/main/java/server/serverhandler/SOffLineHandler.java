package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.OffLinemsg;
import message.ServerToClientmsg;

import java.sql.*;

public class SOffLineHandler extends SimpleChannelInboundHandler<OffLinemsg> {


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
    static PreparedStatement ps = null;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OffLinemsg offLinemsg) throws Exception {

        try{
            System.out.println("打印消息" +offLinemsg);

            int userid2=offLinemsg.getUserid();

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


            String sql2 ="update usermsg set userstate =2 where userid =?";
            ps=conn.prepareStatement(sql2);
            ps.setInt(1,userid2);
            ps.executeUpdate();

            //群聊
            String sql3 ="update grouplist set userstate =2 where userid =?";
            ps=conn.prepareStatement(sql3);
            ps.setInt(1,userid2);
            ps.executeUpdate();


            message1 = new ServerToClientmsg(true,"你的状态已经改为下线");
            System.out.println(message1);
            ctx.writeAndFlush(message1);
            // 完成后关闭
            //rs.close();
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
