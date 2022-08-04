package server.serverhandler;

import common.ChatHandlerMap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.Loginmsg;
import message.Message;
import message.ServerToClientmsg;

import java.sql.*;

public class SLoginViewHandler extends SimpleChannelInboundHandler<Loginmsg> {

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
    protected void channelRead0(ChannelHandlerContext ctx, Loginmsg loginmsg) throws Exception {
        try {

            //每次先打印一下下，看消息发过来没有！！！！！！
            System.out.println("打印消息"+loginmsg);


            //接受消息的部分
            int userid1 =loginmsg.getUserid();
            String password1 = loginmsg.getPassword();

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
            sql = "SELECT userid,userpassword FROM usermsg where (userid=? and userpassword=?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid1);
            ps.setString(2, password1);
            rs = ps.executeQuery();

            //展开结果集数据库
            //next()会将光标向下移动一行，
            //并返回当前行是否有效，如果遍历完成整个表，则会返回false

            boolean isexit =false; //临时变量，判断该用户是否存在,密码是否和数据库中的一样

            while (rs.next()) {
                //通过字段搜索
                int userid2=rs.getInt("userid");
                String password2 = rs.getString("userpassword");
                System.out.println(userid2);
                System.out.println(password2);

                if((userid1==userid2) && (password1.equals(password2))){
                    isexit=true;
                }
            }
            //判断之后进行后续选择
            if(isexit){
                String sql2 ="update usermsg set userstate =1 where userid =?";
                ps=conn.prepareStatement(sql2);
                ps.setInt(1,userid1);
                ps.executeUpdate();
                System.out.println("111111111111ceshi");
                System.out.println("已将您的状态改为上线");

                //群聊表的状态
                String sql3="update grouplist set memberstate =1 where groupmemberid =?";
                ps=conn.prepareStatement(sql3);
                ps.setInt(1,userid1);
                ps.executeUpdate();
                System.out.println("111111111111ceshi");
                System.out.println("已将您群聊表中的状态改为上线");

                //好友表中的状态
                String sql4="update friendlist set state =1 where friendid =?";
                ps=conn.prepareStatement(sql4);
                ps.setInt(1,userid1);
                ps.executeUpdate();
                System.out.println("111111111111ceshi");
                System.out.println("已将您好友表中的状态改为上线");



                message1 = new ServerToClientmsg(true,"数据库核对成功（该账号存在，密码输入正确）");
                System.out.println(message1);

                //将用户的在线状态改为1【在线】，默认是不在线的2
//                String sql2 ="update usermsg set userstate =1 where userid =?";
//                ps=conn.prepareStatement(sql2);
//                ps.setInt(1,userid1);
//                ps.executeUpdate();
//                System.out.println("已将您的状态改为上线");

                //建一个handler
                ChatHandlerMap.add(userid1,ctx.channel());
                System.out.println(userid1 );
                System.out.println(ctx.channel());

                //ctx.writeAndFlush(message1);
            }else{
                message1 = new ServerToClientmsg(false,"您的账号不存在或者密码错误");
                System.out.println(message1);
            }


            message1.setMessageType(Message.Loginmsg);
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
