package server.serverhandler;

import cn.hutool.core.date.DateTime;
import common.ChatHandlerMap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.GroupChatMessage;
import message.ServerToClientmsg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.*;

public class SGroupChatHandler extends SimpleChannelInboundHandler<GroupChatMessage> {

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
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatMessage groupChatMessage) throws Exception {

        try{

            System.out.println("打印消息" + groupChatMessage);

            //接收消息的部分
            int userid1= groupChatMessage.getUserid();
            int groupid1 = groupChatMessage.getGroupid();
            String msg1= groupChatMessage.getMessage();
            String messagetype1=groupChatMessage.getMessagetype();//消息类型

            File file =groupChatMessage.getFile();

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

            String sql1;
            sql1 ="SELECT groupstate FROM groupmsg WHERE groupid=?";
            ps = conn.prepareStatement(sql1);
            ps.setInt(1,groupid1);
            rs = ps.executeQuery();

            int isexit =2;
            while (rs.next()){
                int groupstate =rs.getInt("groupstate");
                System.out.println("群是否处于禁言模式" + groupstate);

                //1表示群不是禁言模式，可以发消息
                if(groupstate==1){
                    isexit=1;
                }
                //2表示群是禁言模式，不可以发消息
                if(groupstate==2){
                    isexit=2;
                }
            }

            //判断群可不可以发消息之后进行下一步
            if(isexit==2){
                message1 = new ServerToClientmsg(false, "该群处于禁言模式，不能发消息");
                System.out.println(message1);
                ctx.writeAndFlush(message1);
            }

            if(isexit==1) {
                message1 = new ServerToClientmsg(true, "该群不是禁言模式，可以开始群聊啦");
                System.out.println(message1);

                System.out.println("打印聊天消息" + groupChatMessage);

                //遍历群列表，谁在线就给谁绑定管道发消息，不在线的将消息存起来
                //如果没有人在线，就把消息存起来，不发就行了


                String sql2;
                sql2 = "SELECT groupmemberid,memberstate FROM grouplist where groupid=?";
                ps = conn.prepareStatement(sql2);
                ps.setInt(1, groupid1);
                rs = ps.executeQuery();
                while (rs.next()) {

                    // 通过字段检索
                    int groupmemberid1 = rs.getInt("groupmemberid");
                    int memberstate1 = rs.getInt("memberstate");

                    if (memberstate1 == 1  && groupmemberid1!=userid1) {
                        //建管道发消息
                        Channel channel;
                        channel = ChatHandlerMap.getChannel(groupmemberid1);
                        groupChatMessage.setA(userid1 + "说：");
                        channel.writeAndFlush(groupChatMessage);

                        message2 = new ServerToClientmsg(true, ""); //唤醒线程
                        ctx.writeAndFlush(message2);

                        //发完消息之后将消息存到数据库

                        String sql3 = "insert into message(groupid,senderid,receiverid,message,issuccess,messagetype,chattype) values(?,?,?,?,?,?,?) ";
                        ps = conn.prepareStatement(sql3);
                        ps.setInt(1, groupid1);
                        ps.setInt(2, userid1);
                        ps.setInt(3, groupmemberid1);
                        // ps.setInt(4, 1);


                        //消息类型要分情况（文件FILE和文本TEXT）
                        if (messagetype1 == "TEXT" || file == null) {
                            ps.setString(4, msg1);
                            ps.setInt(5, 12);
                            ps.setString(6, "TEXT");
                        } else {
                            String addFile;
                            addFile =System.getProperty("user.dir") + file.getName()+new DateTime(System.currentTimeMillis());

                            //测试
                            System.out.println("测试" + addFile);


                            ps.setString(4, addFile); //获取文件的绝对路径

                            //将文件读到程序，然后写入本地存着

                            File tempFile = new File(addFile);
                            tempFile.createNewFile();
                            FileChannel readChannel = new FileInputStream(file).getChannel();
                            FileChannel writeChannel = new FileOutputStream(tempFile).getChannel();
                            ByteBuffer buf = ByteBuffer.allocate(1024);
                            while (readChannel.read(buf) != -1) {
                                buf.flip();
                                writeChannel.write(buf);
                                buf.clear();
                            }

                            readChannel.close();
                            writeChannel.close();

                            ps.setInt(5, 13);
                            ps.setString(6, "FILE");

                            message2 = new ServerToClientmsg(true, ""); //唤醒线程
                            ctx.writeAndFlush(message2);


                        }

                        ps.setString(7, "GROUP");
                        ps.executeUpdate();

                    }
                    if (isexit == 2 && groupmemberid1!=userid1) { //将消息存下来上线了看

                        message2 = new ServerToClientmsg(true, ""); //唤醒线程
                        ctx.writeAndFlush(message2);

                        String sql4 = "insert into message(groupid,senderid,receiverid,message,issuccess,messagetype,chattype) values(?,?,?,?,?,?,?) ";
                        ps = conn.prepareStatement(sql4);
                        ps.setInt(1, groupid1);
                        ps.setInt(2, userid1);
                        ps.setInt(3, groupmemberid1);


                        //消息类型要分情况（文件FILE和文本TEXT）
                        if (messagetype1 == "TEXT" || file == null) {
                            ps.setString(4, msg1);
                            ps.setInt(5, 12);
                            ps.setString(6, "TEXT");
                        } else {
                            String addFile;
                            addFile = System.getProperty("user.dir") + file.getName()+new DateTime(System.currentTimeMillis());

                            //测试
                            System.out.println("测试" + addFile);


                            ps.setString(4, addFile); //获取文件的绝对路径

                            //将文件读到程序，然后写入本地存着

                            File tempFile = new File(addFile);
                            tempFile.createNewFile();
                            FileChannel readChannel = new FileInputStream(file).getChannel();
                            FileChannel writeChannel = new FileOutputStream(tempFile).getChannel();
                            ByteBuffer buf = ByteBuffer.allocate(1024);
                            while (readChannel.read(buf) != -1) {
                                buf.flip();
                                writeChannel.write(buf);
                                buf.clear();
                            }

                            readChannel.close();
                            writeChannel.close();

                            ps.setInt(5, 13);
                            ps.setString(6, "FILE");

                            message2 = new ServerToClientmsg(true, ""); //唤醒线程
                            ctx.writeAndFlush(message2);


                        }

                        ps.setString(7, "GROUP");
                        ps.executeUpdate();

                    }

                }

            }
            rs.close();
            stat.close();
            conn.close();

        }catch(SQLException se)

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

