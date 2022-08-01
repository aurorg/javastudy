package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;

import java.util.Scanner;

public class CPassAddGroupApplyView {

    //用户输入
    static Scanner input = new Scanner(System.in);

    public CPassAddGroupApplyView(ChannelHandlerContext ctx){
        System.out.println("*******************************");
        System.out.println("*     欢迎来到群通知处理界面       *");
        System.out.println("*      根据您的需求进行选择        *");
        System.out.println("*       [1]:同意加群请求         *");
        System.out.println("*       [2]:拒绝加群请求         *");
        System.out.println("*       [3]:暂时不处理           *");
        System.out.println("*       [0]:返回好友界面         *");
        System.out.println("*******************************");

        int n=input.nextInt();
        switch(n){
            case 1:
                passgroupapply(ctx);
                break;
            case 2:
                System.out.println("不通过用户的加群申请，接下来给你返回群管理主界面");
                new CGroupOneViewHandler(ctx);
                break;
            case 3:
                System.out.println("暂不处理就退出啦");
                new CGroupOneViewHandler(ctx);
                break;
            case 0:
                new CGroupOneViewHandler(ctx);
                break;
            default:
                System.out.println("请按照要求输入哦！再给你一次重新输入的机会");
                new CPassAddGroupApplyView(ctx);
        }
    }
//-——————————————————————————————————————————————————————————————————————————————

    public void passgroupapply(ChannelHandlerContext ctx){

    }
}
