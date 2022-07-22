package server.serverhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.sql.*;
import java.util.Scanner;

public class SLoginViewHandle extends  ChannelInboundHandlerAdapter{
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
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//        ByteBuf buf=(ByteBuf) msg;
//        System.out.println("客户端发送的消息是：" + buf.toString(CharsetUtil.UTF_8));
//        int pn1=buf.readInt();
//        System.out.println(pn1);
        //enroll1(pn1);

    }

}
