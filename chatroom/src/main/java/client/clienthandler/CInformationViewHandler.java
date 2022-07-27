package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import message.Informationmsg;

import java.util.List;
import java.util.Scanner;

import static client.ChatNettyClient.*;

public class CInformationViewHandler {
    //用户输入
    static Scanner input = new Scanner(System.in);

    public CInformationViewHandler(ChannelHandlerContext ctx) {
        System.out.println("*******************************");
        System.out.println("*      欢迎来到查看消息界面！      *");
        System.out.println("*      根据您的需求进行选择！      *");
        System.out.println("*     [1]:查看好友历史消息        *");
        System.out.println("*     [2]:查看好友未读消息        *");
        System.out.println("*     [3]:查看群聊历史消息        *");
        System.out.println("*     [4]:查看群聊未读消息        *");
        System.out.println("*     [0]:返回主界面             *");
        System.out.println("*******************************");

        int n = input.nextInt();
        switch (n) {
            case 1:
                //查看好友历史消息
                historyfriendmsg(ctx);
                break;

            case 2:
                //查看好友未读消息函数（这里查的是客户没有上线的时候的消息）
                unreadfriendmsg(ctx);
                break;
            case 3:
                //查看群聊历史消息
                historygroupmsg(ctx);
                break;
            case 4:
                //查看群聊未读消息
                unreadgroupmsg(ctx);
                break;

            case 0:
                //不看了，返回到主界面
                new CMainViewHandler(ctx);
                break;
            default:
                System.out.println("请按照要求输入哦！再给你一次重新输入的机会");
                new CInformationViewHandler(ctx);
        }

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //查看好友历史消息函数
    public void historyfriendmsg(ChannelHandlerContext ctx) {
        System.out.println("请输入您的账号【id号】：");
        int userid1 = input.nextInt();
        System.out.println("请输入您的好友的账号【id号】：");
        int friendid1 = input.nextInt();

        Informationmsg informationmsg = new Informationmsg(userid1, friendid1);
        ctx.writeAndFlush(informationmsg);

        try {
            synchronized (waitMessage) {
                waitMessage.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        List<String> friend=friendlist.get();
//        List<String> group=friendlist.get();
        for (String s1 : friendlist) {
            System.out.println(s1);
        }
//        for(String s1:group){
//            System.out.println(s1);
//        }
            System.out.println("所有的消息如上文，接下来给你返回主界面");
            new CInformationViewHandler(ctx);



    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //查看好友未读消息记录
    public void unreadfriendmsg(ChannelHandlerContext ctx) {

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //查看群聊历史消息
    public void historygroupmsg(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //查看群聊未读消息
    public void unreadgroupmsg(ChannelHandlerContext ctx){
        
    }



}