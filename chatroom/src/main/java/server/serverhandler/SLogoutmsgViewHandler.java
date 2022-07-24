package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.Logoutmsg;
import message.Message;
import message.ServerToClientmsg;

import java.sql.*;

public class SLogoutmsgViewHandler extends SimpleChannelInboundHandler<Logoutmsg> {
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
    protected void channelRead0(ChannelHandlerContext ctx, Logoutmsg logoutmsg) throws Exception {

        try{
            //每次先打印一下下，看消息发过来没有！！！！！！
            System.out.println("打印消息" + logoutmsg);
            int id1=logoutmsg.getUserid();
            System.out.println("客户端传过来要删除的id是" + id1);


            ServerToClientmsg message1;

            //初始化数据库的连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //创建向数据库发送sql的statement对象
            stat = conn.createStatement();

            String sql ="delete from usermsg where userid=?";
            ps =conn.prepareStatement(sql);

           // System.out.println("请再次输入要删除的账号：");
           // int s1 =input.nextInt();
            //ps.setInt(1,s1);

            //删除的mysql语句，然后进行删除
            //count用来返回相应的行数
            //int count = stat.executeUpdate("delete from usemsg where userid= '10'");
            //executeUpdate(String sql)：用于向数据库发送insert、update或delete语句


            rs = ps.executeQuery();
            message1 = new ServerToClientmsg(true,"您的账号已经删除");
            message1.setMessageType(Message.Logoutmsg);
            ctx.writeAndFlush(message1);
//            if(count>0){
//                System.out.println("删除账户成功，受到影响的行数为："+count);
//            }else{
//                System.out.println("删除账户失败");
//            }

            //关闭资源
            //rs.close();
            ps.close();
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
