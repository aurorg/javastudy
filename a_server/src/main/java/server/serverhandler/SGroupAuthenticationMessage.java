package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.GroupAuthenticationMessage;
import message.Message;
import message.ServerToClientmsg;

import java.sql.*;

public class SGroupAuthenticationMessage extends SimpleChannelInboundHandler<GroupAuthenticationMessage> {
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

    //用户输入
//    static Scanner input = new Scanner(System.in);


    @Override
    protected void channelRead0(ChannelHandlerContext  ctx, GroupAuthenticationMessage groupAuthenticationMessage) throws Exception {

        try {
            System.out.println("打印消息测试" + groupAuthenticationMessage);

            int userid1 = groupAuthenticationMessage.getUserid();
            int groupid1 = groupAuthenticationMessage.getGroupid();
            int memberidentity1 = groupAuthenticationMessage.getMemberidentity();

            ServerToClientmsg message1 = null;

            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //获得数据库链接
            //System.out.println("连接数据库.....");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //执行查询
            //System.out.println("实例化Statement对象...");

            //创建传输器
            stat = conn.createStatement();
            //createStatement()：创建向数据库发送sql的statement对象。


            String sql;
            sql = "SELECT memberidentity FROM grouplist where(groupid=? and groupmemberid=?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, groupid1);
            ps.setInt(2, userid1);
            rs = ps.executeQuery();

            boolean isexit = false;

            while (rs.next()) {
                int memberidentity = rs.getInt("memberidentity");

                if (memberidentity == memberidentity1) {
                    isexit = true;
                }
            }
            if (isexit) {
                message1 = new ServerToClientmsg(true, "数据库核对成功（身份验证成功）");
                System.out.println(message1);
            } else {
                message1 = new ServerToClientmsg(false, "您输入的身份有问题哦");
                System.out.println(message1);
            }

            message1.setMessageType(Message.GroupAuthenticationMessage);
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
