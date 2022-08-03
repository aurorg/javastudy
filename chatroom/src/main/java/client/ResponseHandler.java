package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.Message;
import message.ServerToClientmsg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

import static client.ChatNettyClient.*;

public class ResponseHandler extends SimpleChannelInboundHandler<ServerToClientmsg> {

    static Scanner input = new Scanner(System.in);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ServerToClientmsg message) throws Exception {

        boolean success = message.getSuccess();
        String reason = message.getReason();
        int ResponseMessageType = message.getMessageType();

        if (!success) {
            System.out.print("操作失败 " + reason);
            waitSuccess = 0;
        }
        //成功里面分好多种情况

        else {
            waitSuccess = 1;
//            if(message.getMessageType()== Message.Informationmsg){
//                informationMap=message.getInformationMap();
//
//            }

            //System.out.println(333);
//            unRead=message.getReadcount();
//            havefile=message.getHavefile();

            if (message.getMessageType() == Message.Informationfriendhistorymsg) {
                friendmsglist = message.friendmsglist;
            }
            else if (message.getMessageType() == Message.FriendGetFilemsg) {
                System.out.println("接下来输入您的本地目录保存下来：");
                saveFile(message.getFile());//调用将文件保存到本地目录的方法
                System.out.println("message.file的路径【测试路径1】"+message.getFile());
                System.out.println("已经保存啦！");
            }
            else if (message.getMessageType() == Message.Informationfriendunreadmsg) {
                friendmsglist1 = message.friendmsglist1;
            }
            else if (message.getMessageType() == Message.FriendListmsg) {
                friendlist = message.friendlist;
            }
            else if(message.getMessageType()==Message.GroupListMember){
                grouplist=message.grouplist;
            }
            else if(message.getMessageType()==Message.GroupHistorymsg){
                grouphistorymsg=message.grouphistorymsg;
            }
            else if(message.getMessageType()==Message.GroupUnreadmsg){
                groupunreadmsg=message.groupunreadmsg;
            }

            else {
                System.out.print("操作成功 " + reason);
                waitSuccess = 1;

                synchronized (waitMessage) {
                    waitMessage.notifyAll();
                }

            }
        }

        //不管操作成功还是失败都需要唤醒界面的主线程
        synchronized (waitMessage) {
            waitMessage.notifyAll();
        }
    }

    //用来接受文件的时候的输入存到本地的路径
    public void saveFile(File file) {
        try {
//            System.out.println("请输入需要保存文件的路径：");
//            Scanner scanner=new Scanner(System.in);
//            String addFile=scanner.next();

//            File tempFile1=new File(addFile);

//            while(!tempFile1.isDirectory()){
//                System.out.println("不是目录，请重新输入：");
//                addFile=scanner.nextLine();
//                tempFile1=new File(addFile);
//            }
//            if(addFile.charAt(addFile.length()-1)!='/'){
//                addFile=addFile.concat("/");
//            }

            //    addFile=addFile.concat(file.getName());
//            tempFile1=new File(addFile);
//
//            FileChannel readChannel1= new FileInputStream(file).getChannel();
//            FileChannel writeChannel1= new FileOutputStream(tempFile1).getChannel();
//
//            ByteBuffer buf=ByteBuffer.allocate(1024);
//            while(readChannel1.read(buf)!=-1){
//                buf.flip();
//                writeChannel1.write(buf);
//                buf.clear();
//            }
//            tempFile1.createNewFile();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
            System.out.println("请输入路径：");
            String addFile = input.next();
            File tempFile1 = new File(addFile);
            tempFile1.createNewFile();
            System.out.println("查看路径【测试路径2】"+file.getAbsolutePath());
            FileChannel readChannel = new FileInputStream(file).getChannel();
            FileChannel writeChannel = new FileOutputStream(tempFile1).getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (readChannel.read(buf) != -1) {
                buf.flip();
                writeChannel.write(buf);
                buf.clear();
            }

            readChannel.close();
            writeChannel.close();


        } catch (IOException e) {
//            e.printStackTrace();
//        }
        }
    }
}
