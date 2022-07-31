package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import message.GroupAuthenticationMessage;

import java.util.Scanner;

import static client.ChatNettyClient.waitMessage;
import static client.ChatNettyClient.waitSuccess;

public class CGroupViewHandler {
    //用户输入
    static Scanner input = new Scanner(System.in);

    public CGroupViewHandler(ChannelHandlerContext ctx){

        //确认了之后进行下一步选择

        System.out.println("********************************");
        System.out.println("*         欢迎来到群界面！         *");
        System.out.println("*       根据您的身份进行选择！      *");
        System.out.println("*        [1]:群主               *");
        System.out.println("*        [2]:群管理员            *");
        System.out.println("*        [3]:普通用户            *");
        System.out.println("*        [0]:返回主界面          *");
        System.out.println("********************************");

        int n=input.nextInt();
        switch(n){
            case 1:
                //群主界面
                authentication(ctx);
                new CGroupOneViewHandler(ctx);
                break;
            case 2:
                //群管理员界面
                authentication(ctx);
                new CGroupTwoViewHandler(ctx);
                break;
            case 3:
                //普通用户界面
                authentication(ctx);
                new CGroupThreeViewHandler(ctx);
                break;
            case 0:
                System.out.println("接下来即将为你返回主界面！");
                new CMainViewHandler(ctx);
                break;
            default:
                System.out.println("请按照要求输入哦，再给你一次机会");
                new CGroupViewHandler(ctx);

        }
    }

    public void authentication(ChannelHandlerContext ctx){
        System.out.println("请输入你的账号：");
        int userid = input.nextInt();

        System.out.println("请输入你需要访问的群id:");
        int groupid = input.nextInt();

        System.out.println("请输入你在这个群里的身份:");
        int identity = input.nextInt();

        //给服务器发消息，服务器从数据库查看数据，确认你的身份是否正确

        GroupAuthenticationMessage groupAuthenticationMessage = new GroupAuthenticationMessage(userid, groupid,identity);
        ctx.writeAndFlush(groupAuthenticationMessage);

        //这里需要加锁，服务端返回消息后，客户端继续
        try {
            synchronized (waitMessage) {
                waitMessage.wait();

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //如果输入的身份和数据库的身份一样就进行下一步操作
        //如果输入的身份不一样的话就重新输入
        if (waitSuccess == 1) {
            System.out.println("身份验证正确！");
            System.out.println("可以开始下一步操作");

        }else{
            System.out.println("您输入的身份不正确哦！");
            System.out.println("请重新输入您的身份");
            authentication(ctx);
        }


    }






}
