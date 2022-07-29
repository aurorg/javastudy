package server.serverhandler;

import common.ChatHandlerMap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.FriendChatmsg;
import message.ServerToClientmsg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.*;

public class SFriendChatHandler extends SimpleChannelInboundHandler<FriendChatmsg> {

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
    protected void channelRead0(ChannelHandlerContext ctx, FriendChatmsg friendChatmsg) throws Exception {

        try{

        //每次先打印一下下，看消息发过来没有！！！！！！
//            int cishu1 =friendChatmsg.getCishu();
//            if(cishu1==1){
//                System.out.println("打印消息1" + friendChatmsg);
//            }
//            else{
//                System.out.println("打印消息2" + friendChatmsg);
//            }

        System.out.println("打印消息" + friendChatmsg);

        //接受消息的部分
        int userid1 = friendChatmsg.getUserid();
        int friendid1 = friendChatmsg.getFriendid();
        String msg1 = friendChatmsg.getMessage();
        String  messagetype=friendChatmsg.getMessagetype(); //消息的类型FILE 和TEXT

        File file=friendChatmsg.getFile();


        ServerToClientmsg message1 = null;
        ServerToClientmsg message2 = null;


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
        sql = "SELECT isfriend,isshield,state FROM friendlist where (userid=? and friendid=?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, userid1);
        ps.setInt(2, friendid1);
//        ps.setInt(3, friendid1);
//        ps.setInt(4, userid1);
        rs = ps.executeQuery();

        //ps.executeUpdate();


        //传输sql并且返回结果
        //ResultSet rs = stat.executeQuery(sql); //executeQuery(String sql) ：用于向数据发送查询语句

        //展开结果集数据库
        //next()会将光标向下移动一行，
        //并返回当前行是否有效，如果遍历完成整个表，则会返回false

        int isexit = 0; //临时变量，判断用户和好友之间可以不可以通讯
        //0: 表示之间不能通信（是因为两者之间不是好友）
        //3：表示之间不能通信（是因为你和对方处于屏蔽状态）
        //1：表示之间可以通信（是好友，没有屏蔽好友，好友在线）
        //2：表示消息可以发出去，但是对方是离线状态不接受（是好友，没有屏蔽好友，对方不在线）

        while (rs.next()) {


            // 通过字段检索
            int isfriend1 = rs.getInt("isfriend");
            int isshield1 = rs.getInt("isshield");
            int state1 = rs.getInt("state");

            System.out.println("是否好友:" + isfriend1 + "是否屏蔽:" + isshield1 + "是否在线:" + state1);
            if (isfriend1 == 1 && isshield1 == 1 && state1 == 1) {
                isexit = 1;
            } else if (isfriend1 == 1 && isshield1 == 1 && state1 == 2) {
                isexit = 2;
            } else if (isfriend1 == 1 && isshield1 == 2) {
                isexit = 3;
            }

        }

        //判断之后进行后续选择

            /**
             * 【我的id:11,好友id:31】：不是好友，屏蔽了(这里屏蔽不屏蔽无所谓)，好友在线
              */
            //0: 表示之间不能通信（是因为两者之间不是好友）
        if (isexit == 0) {
            message1 = new ServerToClientmsg(false, "您和对方还不是好友");
            System.out.println(message1);
           ctx.writeAndFlush(message1);

        }

        /**
         * 【我的id:4，好友的id：24】：是好友，屏蔽了，在线
         */
        //3：表示之间不能通信（是因为你和对方处于屏蔽状态）
        else if (isexit == 3) {
            message1 = new ServerToClientmsg(false, "您和您的好友处于屏蔽状态");
            System.out.println(message1);
            ctx.writeAndFlush(message1);
            
        }

        /**
         * 已经测试过，可以发；
         * 【我的id:1,好友id：22】:是好友，没有屏蔽，都在线
         */
        //1：表示之间可以通信（是好友，没有屏蔽好友，好友在线）【消息可以存到数据库并且可以发出去】
        else if (isexit == 1) {

            message1 = new ServerToClientmsg(true, "您和您的好友可以开始聊天啦");
            System.out.println(message1);

            //打印消息
            //System.out.println("111111111");
            System.out.println("打印消息"+friendChatmsg);

            Channel channel;
            channel= ChatHandlerMap.getChannel(friendid1);
            channel.writeAndFlush(friendChatmsg);

           message2 = new ServerToClientmsg(true, ""); //唤醒线程
           ctx.writeAndFlush(message2);

//            message1 = new ServerToClientmsg(true, "您和您的好友可以开始聊天啦");
//            System.out.println(message1);


            
            String sql1 = "insert into message(senderid,receiverid,message,issuccess,messagetype,chattype) values(?,?,?,?,?,?) ";
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, userid1);
            ps.setInt(2, friendid1);
           // ps.setInt(4, 1);


            //消息类型要分情况（文件FILE和文本TEXT）
            if(messagetype=="TEXT" || file==null){
                ps.setString(3, msg1);
                ps.setInt(4, 1);
                ps.setString(5,"TEXT");
            }else {
                String addFile;
                addFile="/home/shizhanli/szl/" + file.getName();

                //测试
                System.out.println("测试"+addFile);

                ps.setString(3,addFile); //获取文件的绝对路径

                //将文件读到程序，然后写入本地存着

                File tempFile=new File(addFile);
                tempFile.createNewFile();
                FileChannel readChannel= new FileInputStream(file).getChannel();
                FileChannel writeChannel= new FileOutputStream(tempFile).getChannel();
                ByteBuffer buf=ByteBuffer.allocate(1024);
                while(readChannel.read(buf)!=-1){
                    buf.flip();
                    writeChannel.write(buf);
                    buf.clear();
                }

                readChannel.close();
                writeChannel.close();

                ps.setInt(4, 1);
                ps.setString(5,"FILE");

            }

            ps.setString(6,"FRIEND");
            ps.executeUpdate();

            // ResultSet rs2 = stat.executeQuery(sql1);
        }

        
        /**
         *
         * 【我的id:2,好友id：23】：是好友，没有屏蔽好友，对方不在线
         */
        //2：表示消息可以发出去，但是对方是离线状态不接受（是好友，没有屏蔽好友，对方不在线）【消息可以存到数据库，但是不能发出去】
        else if(isexit ==2){

            message1 = new ServerToClientmsg(false, "您的好友没有上线哦，上线之后才可以看到消息");
            System.out.println(message1);
            ctx.writeAndFlush(message1);

            String sql1 = "insert into message(senderid,receiverid,message,issuccess,messagetype,chattype) values(?,?,?,?,?,?) ";
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, userid1);
            ps.setInt(2, friendid1);
            //ps.setString(3,msg1 );
            //ps.setInt(4, 2);


            String addFile= file.getAbsolutePath();

            //消息类型要分情况（文件FILE和文本TEXT）
            if(messagetype=="TEXT" || file==null){
                ps.setString(3, msg1);
                ps.setInt(4, 2);
                ps.setString(5,"TEXT");
            }else{


                addFile="/home/shizhanli/szl/" + file.getName();

                //测试
                System.out.println("测试"+addFile);

                ps.setString(3,addFile); //获取文件的绝对路径

                //将文件读到程序，然后写入本地存着

                File tempFile=new File(addFile);
                tempFile.createNewFile();
                FileChannel readChannel= new FileInputStream(file).getChannel();
                FileChannel writeChannel= new FileOutputStream(tempFile).getChannel();
                ByteBuffer buf=ByteBuffer.allocate(1024);
                while(readChannel.read(buf)!=-1){
                    buf.flip();
                    writeChannel.write(buf);
                    buf.clear();
                }

                readChannel.close();
                writeChannel.close();


                ps.setInt(4, 2);
                ps.setString(5,"FILE");

            }

            ps.setString(6,"FRIEND");
            ps.executeUpdate();

        }

//        message1.setMessageType(Message.FriendChatmsg);
//        ctx.writeAndFlush(message1);

        // 完成后关闭
        //rs.close();
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


