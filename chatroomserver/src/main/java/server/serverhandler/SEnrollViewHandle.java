package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.Enrollmsg;
import message.Message;
import message.ServerToClientmsg;

import java.sql.*;

public class SEnrollViewHandle extends  SimpleChannelInboundHandler<Enrollmsg>{
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
    protected void channelRead0(ChannelHandlerContext ctx,Enrollmsg message) throws Exception {
        try {

            //每次先打印一下下，看消息发过来没有！！！！！！
            System.out.println("打印消息"+message);


            //接受消息的部分
            int pn1 =message.getPhonenumber();
            System.out.println(pn1);
            String name1 =message.getName();
            String password1 =message.getPassword();

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
            sql = "SELECT phonenumber FROM usermsg";

            //传输sql并且返回结果
            ResultSet rs = stat.executeQuery(sql); //executeQuery(String sql) ：用于向数据发送查询语句

            //展开结果集数据库
            //next()会将光标向下移动一行，
            //并返回当前行是否有效，如果遍历完成整个表，则会返回false

            boolean isexit =false; //临时变量，判断该电话号码是否存在

            while (rs.next()) {

                // 通过字段检索
                int phonenumber =rs.getInt("phonenumber");
                if(pn1==phonenumber){
                    isexit =true;
                }

            }
            //判断之后进行后续选择
            if(isexit){
                 message1 = new ServerToClientmsg(false,"您的手机号已经注册过账号");
                System.out.println(message1);
                ctx.writeAndFlush(message1);

            }
            else{
                message1 = new ServerToClientmsg(true,"注册成功");
                String sql1 ="insert into usermsg(username,userpassword,phonenumber) values(?,?,?) ";
                ps=conn.prepareStatement(sql1);
                ps.setString(1,name1);
                ps.setString(2,password1);
                ps.setInt(3,pn1);
                ps.executeUpdate();
               // ResultSet rs2 = stat.executeQuery(sql1);

                String sql2 ="SELECT userid FROM usermsg where phonenumber = ? ";
                ps=conn.prepareStatement(sql2);
                ps.setInt(1,pn1);
                rs = ps.executeQuery();
                while (rs.next()) {

                    // 通过字段检索
                    int userid =rs.getInt("userid");
                    System.out.println("您的账号是：" + userid);
                    message1 = new ServerToClientmsg(true,"您的Id号是（以后登录的账号）" + userid);
//                    System.out.println(message1);
//                    ctx.writeAndFlush(message1);
                }

            }
            message1.setMessageType(Message.Enrollmsg);
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


