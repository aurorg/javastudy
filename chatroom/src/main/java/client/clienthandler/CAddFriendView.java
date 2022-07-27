package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;

import java.util.Scanner;

public class CAddFriendView {
    //用户输入
    static Scanner input = new Scanner(System.in);

    public CAddFriendView(ChannelHandlerContext ctx) {
        System.out.println("*******************************");
        System.out.println("*       欢迎来到添加好友界        *");
        System.out.println("*      根据您的需求进行选择        *");
        System.out.println("*       [1]:同意好友请求         *");
        System.out.println("*       [2]:拒绝好友请求         *");
        System.out.println("*       [0]:暂时不处理           *");
        System.out.println("*******************************");

        int n = input.nextInt();
        switch (n) {
            case 1:
                passfriend(ctx);
                break;
            case 2:
                unpassfriend(ctx);
                break;
            case 0:
                System.out.println("暂时不处理就退出啦！");
                ctx.channel().close();
            default:
                System.out.println("请按照要求输入哦！再给你一次重新输入的机会");
                new CAddFriendView(ctx);
                
        }
    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void  passfriend(ChannelHandlerContext ctx){


    }

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void unpassfriend(ChannelHandlerContext ctx){

    }


}
