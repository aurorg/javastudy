package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;

import java.util.Scanner;

public class CInformationViewHandler {
    //用户输入
    static Scanner input = new Scanner(System.in);
    public CInformationViewHandler(ChannelHandlerContext ctx){
        System.out.println("*******************************");
        System.out.println("*      欢迎来到查看消息界面！      *");
        System.out.println("*      根据您的需求进行选择！      *");
        System.out.println("*       [1]:查看未读消息         *");
        System.out.println("*       [2]:查看历史消息         *");
        System.out.println("*       [0]:返回主界面           *");
        System.out.println("*******************************");

       int n =input.nextInt();
       switch(n){
           case 1:
               //查看未读消息函数
               unreadmsg(ctx);
               break;

           case 2:
               //查看历史消息记录
               historymsg(ctx);
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


    //查看未读消息函数
    public void unreadmsg(ChannelHandlerContext ctx){

    }

    //查看历史消息记录
    public void historymsg(ChannelHandlerContext ctx){

    }

}
