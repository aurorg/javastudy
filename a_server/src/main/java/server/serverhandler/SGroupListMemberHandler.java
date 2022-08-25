package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.GroupListMemberMessage;
import message.Message;
import message.ServerToClientmsg;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SGroupListMemberHandler extends SimpleChannelInboundHandler<GroupListMemberMessage> {

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
    protected void channelRead0(ChannelHandlerContext ctx, GroupListMemberMessage groupListMemberMessage) throws Exception {
        try {

            //每次先打印一下下，看消息发过来没有！！！！！！
            System.out.println("打印消息是"+groupListMemberMessage);


            //接受消息的部分
            int userid1= groupListMemberMessage.getGroupid();
            int groupid1 = groupListMemberMessage.getGroupid();

            // ServerToClientmsg message1 = null;

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
            sql = "SELECT groupmemberid,memberidentity FROM grouplist where groupid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, groupid1);

            rs=ps.executeQuery();


            List<String> grouplist =new ArrayList<>();
            while (rs.next()) {

                // 通过字段检索
                int groupmemberid1 =rs.getInt("groupmemberid");
                int memberidentity1=rs.getInt("memberidentity");


                //输出数据
                System.out.println("群成员id: " + groupmemberid1 + " ,该成员的身份【1群主2管理员3普通用户】: " + memberidentity1);


                //加到列表里面去
                grouplist.add("群成员id： " + groupmemberid1 + " ,该成员的身份【1群主2管理员3普通用户】：" + memberidentity1);



            }
            //判断之后进行后续选择
            // System.out.println("11111111");
            ServerToClientmsg message1 = new ServerToClientmsg(true,"群成员列表如下：");
            // System.out.println("111111112222");
            message1.setGrouplist(grouplist);
            // System.out.println("11111111333333333");
            message1.setMessageType(Message.GroupListMember);
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
