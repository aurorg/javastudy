package client.clienthandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;

/**
 * 主要包含了
 * 【1】用户注册账号界面
 * 【2】用户登录账号界面
 * 【3】用户注销账号界面
 * 完成上面这三个操作之后进行后续操作，就会调用MainView里面的界面
 */
public class CLoginViewHandler{
    static Scanner input = new Scanner(System.in);
    //实现界面层和客户交流的代码
    public CLoginViewHandler(ChannelHandlerContext ctx){
        System.out.println("*******************************");
        System.out.println("*        欢迎来到用户界面        *");
        System.out.println("*       根据您的需求进行选择      *");
        System.out.println("*         [1]:用户注册          *");
        System.out.println("*         [2]:用户登录          *");
        System.out.println("*         [3]:用户注销          *");
        System.out.println("*******************************");

        int n = input.nextInt();
        switch (n) {
            case 1:
               // enroll(ctx);
                break;
            case 2:
               // login();
                break;
            case 3:
               // logout();
                break;
            default:
                System.out.println("请按照要求输入哦！");
                System.exit(0);
        }

    }




}
