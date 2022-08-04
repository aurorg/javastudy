package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.FindPasswordmsg;
import message.ServerToClientmsg;

import java.sql.*;

public class SFindPasswordHandler extends SimpleChannelInboundHandler<FindPasswordmsg> {
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
    protected void channelRead0(ChannelHandlerContext ctx, FindPasswordmsg findPasswordmsg) throws Exception {
        try {
            System.out.println("打印消息" + findPasswordmsg);

            //接受消息的部分

            int userid1 = findPasswordmsg.getUserid();
            int phonenumber1 = findPasswordmsg.getPhonenumber();


            ServerToClientmsg message1;

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
            sql = "SELECT phonenumber FROM usermsg WHERE userid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid1);
            //传输sql并且返回结果
            rs =ps.executeQuery(); //executeQuery(String sql) ：用于向数据发送查询语句
            while (rs.next()) {

                // 通过字段检索
                int phonenumber = rs.getInt("phonenumber");
                System.out.println("您的电话号是：" + phonenumber);
                int temp=(int)(Math.random()*100000+1);
                int verificationcode =(temp+phonenumber)%1000;
                message1 = new ServerToClientmsg(true, "您的验证码【暂时登录的密码】：" + verificationcode);
                ctx.writeAndFlush(message1);

                String sql2 ="update usermsg set userpassword =? where userid =?";
                ps=conn.prepareStatement(sql2);
                ps.setInt(1,verificationcode);
                ps.setInt(2,userid1);
                ps.executeUpdate();

            }



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
