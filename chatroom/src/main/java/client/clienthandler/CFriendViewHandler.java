package client.clienthandler;

import client.ResponseHandler;
import io.netty.channel.ChannelHandlerContext;
import jdk.swing.interop.SwingInterOpUtils;
import message.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

import static client.ChatNettyClient.*;


public class CFriendViewHandler {
    //用户输入
    static Scanner input = new Scanner(System.in);

    public CFriendViewHandler(ChannelHandlerContext ctx){
        System.out.println("*******************************");
        System.out.println("*        欢迎来到好友界面        *");
        System.out.println("*       根据您的需求进行选择      *");
        System.out.println("*         [1]:添加好友          *");
        System.out.println("*         [2]:删除好友          *");
        System.out.println("*         [3]:好友列表          *");
       // System.out.println("*         [4]:查询好友          *");
        System.out.println("*         [4]:屏蔽好友          *");
        System.out.println("*         [5]:好友聊天          *");
        System.out.println("*         [6]:查看好友申请       *");
        System.out.println("*         [0]:返回主界面         *");
        System.out.println("*******************************");
//        if(unRead==true){
//            System.out.println("有未读的消息");
//        }

        //暂时先不处理
//        String n1 = input.nextLine();
//        while(!StringUtils.isNumber(n1)){
//            System.out.println("输入不规范，请重新输入您的选择：");
//            n1=input.nextInt();
//        }
       int n = input.nextInt();

        switch (n) {
            case 1:
                //添加好友
                addfriend(ctx);
                break;
            case 2:
                //删除好友
                deletefriend(ctx);
                break;
            case 3:
                //好友列表
                friendlist(ctx);
                break;
            case 4:
                //屏蔽好友
                shieldfriend(ctx);
                break;
            case 5:
                //好友聊天
                friendchat(ctx);
                break;
            case 6:
                //查看好友的申请
                new CPassFriendApplyView(ctx);
                break;
            case 0:
                //返回主界面
                new CMainViewHandler(ctx);
                break;
            default:
                System.out.println("请按照要求输入哦！");
                new CFriendViewHandler(ctx);
         }
        }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //1  添加好友

    /**
     * 暂时是确保对方在线，绑定管道，将申请发给对方，发申请的信息存在message表中，等待对方去查看好友申请
     * @param ctx
     */

    public void addfriend(ChannelHandlerContext ctx){
        System.out.println("请输入您的账号【id】：");
        int userid1 = input.nextInt();
        System.out.println("请输入您需要添加好友的账号【id】");
        int friendid1=input.nextInt();
        SendApplyMessage sendapplymessage = new SendApplyMessage(userid1,friendid1,"对方想加你好友");
        ctx.writeAndFlush(sendapplymessage);
        try{
            synchronized(waitMessage){
                waitMessage.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("接下来返回好友界面，您可以根据您的需要选择功能");
        new CFriendViewHandler(ctx);

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //2  删除好友
    /**
     * 输入您的id号和删除好友的id号，从friendlist表中删除数据
     * @param ctx
     */
    public void deletefriend(ChannelHandlerContext ctx){
        System.out.println("请输入您的账号【id】：");
        int userid1 = input.nextInt();
        System.out.println("请输入您需要删除好友的账号【id】");
        int friendid1=input.nextInt();
        DeleteFriendmsg deletefriendmsg = new DeleteFriendmsg(userid1,friendid1);
        ctx.writeAndFlush(deletefriendmsg);
        try{
            synchronized(waitMessage){
                waitMessage.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("接下来返回好友界面，您可以根据您的需要选择功能");
        new CFriendViewHandler(ctx);

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    /**
     * 输入您的id号，和要查询的好友id号，服务端从数据库中查询你的所有好友，添加到List中。客户端再打印出来
     * @param ctx
     */

    //3  好友列表
    public void friendlist(ChannelHandlerContext ctx){
        System.out.println("请输入您的账号【id】：");
        int userid1 = input.nextInt();
        FriendListmsg friendListmsg = new FriendListmsg(userid1);
        ctx.writeAndFlush(friendListmsg);

        try{
            synchronized(waitMessage){
                waitMessage.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        for (String s1 : friendlist) {
            System.out.println(s1);
        }

        System.out.println("您的好友列表入上文所显示。");
        System.out.println("接下来返回好友界面，您可以根据您的需要选择功能");
        new CFriendViewHandler(ctx);

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //4  屏蔽好友
    public void shieldfriend(ChannelHandlerContext ctx){
        System.out.println("请输入您的账号【id】：");
        int userid1 = input.nextInt();
        System.out.println("请输入您需要屏蔽好友的账号【id】");
        int friendid1=input.nextInt();
        ShieldFriendmsg shieldFriendmsg=new ShieldFriendmsg(userid1,friendid1);
        ctx.writeAndFlush(shieldFriendmsg);

        try{
            synchronized(waitMessage){
                waitMessage.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("接下来返回好友界面，您可以根据您的需要选择功能");
        new CFriendViewHandler(ctx);

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //5  好友聊天

    /**
     * 1、输入好友的id账号
     * 2、发消息给服务端告诉好友的id号，
     * 3、服务器去查找是否好友，是否被屏蔽，是否离线，发消息告诉给客户端
     * 4 【①不是好友，被屏蔽了满足其一】都不能发消息（返回界面）
     *   【②离线的话可以发消息存到数据库中，好友收不到】（发完返回界面层）
     *   【③是好友，没有被屏蔽，没有离线】就可以开始聊天
     *
     */

    public void friendchat(ChannelHandlerContext ctx){


//        Informationfriendhistorymsg message1 =new Informationfriendhistorymsg(userid1,friendid1);
//        ctx.writeAndFlush(message1);
//
//        try{
//            synchronized(waitMessage){
//                waitMessage.wait();
//            }
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//
//        if(waitSuccess==1){
//            talker=friendid1;
//            int count=0;
//
//            for(String s:friendlist){
//                System.out.println(s);
//            }
//
//            if(havefile.charAt(count)=='1'){
//                CFriendViewHandler.receiverFile(s,input,ctx,friendid1);
//            }
//            count++;
//        }
//
//        Informationmsg message1 = new Informationmsg(userid1,friendid1);
//        ctx.writeAndFlush(message1);
//

//        FriendChatmsg message1 = new FriendChatmsg(userid1,friendid1,null,null);
//        ctx.writeAndFlush(message1);
//
//        try{
//            synchronized(waitMessage){
//                waitMessage.wait();
//            }
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }

        System.out.println("请输入您的id：");
        int userid1=input.nextInt();

        System.out.println("请输入你需要发消息的好友id:");
        int friendid1 = input.nextInt();

        /**
         * 让服务端建立一个channel，用map将（用户和对应的channel绑起来），然后开始发消息
         */
        //System.out.println("\n");
        //输入F表示发文件
        System.out.println("[输入Q，返回主界面(F发文件Y收文件）]：");
        is1=true;
        String chatmessage=input.nextLine(); //输入聊天消息的

        // 暂时有个问题，有一个退出聊天界面了，另外一个就退出不了了

    while(!chatmessage.equals("Q")) {

        //FriendChatmsg friendChatmsg2 = new FriendChatmsg(userid1, friendid1, chatmessage, "TEXT");
        //ctx.writeAndFlush(friendChatmsg2);

        FriendChatmsg friendChatmsg2;


        //发送文件的情况
        if(chatmessage.equalsIgnoreCase("F")){

            File file;
            System.out.println("请输入需要发送的文件的绝对路径：");
            file=new File(input.next());

            while(!file.exists()||!file.isFile()){
                if(!file.exists()){
                    System.out.println("文件不存在，请重新输入需要发送的文件的绝对路径");
                }else{
                    System.out.println("不是文件，请重新输入需要发送的文件的绝对路径");
                }
                file=new File(input.nextLine());

        }
            friendChatmsg2 = new FriendChatmsg(userid1, friendid1, file, "FILE");
            ctx.writeAndFlush(friendChatmsg2);

        try {
            synchronized (waitMessage) {
                waitMessage.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            if(waitSuccess==0){
                new CFriendViewHandler(ctx);
            }
//
//        System.out.println("[输入Q,返回主界面]：");
//
//        chatmessage = input.nextLine();

       // System.out.println("hahahahah" +chatmessage + "2222222222222");
        //System.out.println(chatmessage.length());

        }

        //接收文件的情况
        else if(chatmessage.equalsIgnoreCase("Y")){
            receiveFile(ctx,friendid1,userid1);
        }

        //发送文本消息
        else{
            friendChatmsg2 = new FriendChatmsg(userid1, friendid1, chatmessage, "TEXT");
            ctx.writeAndFlush(friendChatmsg2);
            try{
                synchronized(waitMessage){
                    waitMessage.wait();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if(waitSuccess==0){
                new CFriendViewHandler(ctx);
            }

        }
        System.out.println("[输入Q返回主界面(F发文件Y收文件）]：");
        chatmessage=input.next();
    }

    if(chatmessage.equals("Q")) {
        is1 = false;
        new CFriendViewHandler(ctx);
     }

    }

    //接收文件的方法
    public static void receiveFile(ChannelHandlerContext ctx,int friendid,int userid){
        System.out.println("*********************");
        System.out.println("*你的好友给你发了一个文件*");
        System.out.println("*******[Y]:接受*******");
        System.out.println("*******[N]:拒绝*******");
        System.out.println("*******[S]:不处理******");
        System.out.println("*********************");

        String choice=input.nextLine();
        while(!choice.equalsIgnoreCase("Y")&&!choice.equalsIgnoreCase("N")&&!choice.equalsIgnoreCase("S")){
            System.out.println("请输入");
            choice=input.nextLine();
        }

        //接收文件的情况
        if(choice.equalsIgnoreCase("Y")) {

            System.out.println("你需要接受哪个文件(输入文件路径)：");
            String filename = input.next();

            FriendGetFilemsg friendGetFilemsg = new FriendGetFilemsg(userid, friendid, filename);
            ctx.writeAndFlush(friendGetFilemsg);
            System.out.println("1111111111111111111");
            try {
                synchronized (waitMessage) {
                    waitMessage.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("222222222222222");
            //new ResponseHandler().saveFile(FriendGetFilemsg);
            System.out.println("333333333333333");

        }
        //不接收文件的情况
        else if(choice.equalsIgnoreCase("N")){
//            FriendGetFilemsg friendGetFilemsg=new FriendGetFilemsg(userid,friendid,"拒绝文件接受消息");
//            friendGetFilemsg.setRefuse(true);
//            ctx.writeAndFlush(friendGetFilemsg);
//            System.out.println("3333333333333333");
//            try{
//                synchronized(waitMessage){
//                    waitMessage.wait();
//                }
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
            System.out.println("已经给您拒绝，接下来返回主界面");
            new CFriendViewHandler(ctx);
        }

        //忽略文件，返回界面
        else{
            System.out.println("暂不处理就返回好友界面");
            new CFriendViewHandler(ctx);
        }
    }
//    public static void saveFile(File file){
//        try{
//
//            Scanner scanner=new Scanner(System.in);
//            String addFile=scanner.nextLine();
//
//            File tempFile1=new File(addFile);
//
//            while(!tempFile1.isDirectory()){
//                System.out.println("不是目录，请重新输入：");
//                addFile=scanner.nextLine();
//                tempFile1=new File(addFile);
//            }
//            if(addFile.charAt(addFile.length()-1)!='/'){
//                addFile=addFile.concat("/");
//            }
//            addFile=addFile.concat(file.getName());
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
//    }

}
