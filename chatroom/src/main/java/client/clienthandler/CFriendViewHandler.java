package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import message.*;

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
        System.out.println("111111111111");
        //这里有问题！！！！！！
        for (String s1 : friendlist) {
            System.out.println(s1);
        }
        System.out.println("2222222222222");

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
        System.out.println("请输入您的id：");
        int userid1=input.nextInt();

        System.out.println("请输入你需要发消息的好友id:");
        int friendid1 = input.nextInt();
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

        /**
         * 让服务端建立一个channel，用map将（用户和对应的channel绑起来），然后开始发消息
         */
        //System.out.println("\n");
        System.out.println("[输入Q，返回主界面]：");
        is1=true;
        String chatmessage=input.next(); //输入聊天消息的

        //暂时这里有问题，只能发一条消息，退出不了

    while(!chatmessage.equals("Q")) {

        FriendChatmsg friendChatmsg2 = new FriendChatmsg(userid1, friendid1, chatmessage, "T");
        ctx.writeAndFlush(friendChatmsg2);

        try {
            synchronized (waitMessage) {
                waitMessage.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[输入Q,返回主界面]：");
        chatmessage = input.nextLine();
    }

    if(chatmessage.equals("Q")) {
        is1 = false;
        new CFriendViewHandler(ctx);
    }


    }


}
