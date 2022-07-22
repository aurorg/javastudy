package temp.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.Scanner;

public class Test1 {
}

//第一次有问题的CLoginViewHandler
//package client.clienthandler;
//
//        import io.netty.buffer.ByteBuf;
//        import io.netty.channel.ChannelHandlerContext;
//        import io.netty.channel.ChannelInboundHandlerAdapter;
//
//        import java.util.Scanner;
//
///**
// * 主要包含了
// * 【1】用户注册账号界面
// * 【2】用户登录账号界面
// * 【3】用户注销账号界面
// * 完成上面这三个操作之后进行后续操作，就会调用MainView里面的界面
// */
//public class CLoginViewHandler{
//    static Scanner input = new Scanner(System.in);
//    //实现界面层和客户交流的代码
//    public CLoginViewHandler(ChannelHandlerContext ctx){
//        System.out.println("*******************************");
//        System.out.println("*        欢迎来到用户界面        *");
//        System.out.println("*       根据您的需求进行选择      *");
//        System.out.println("*         [1]:用户注册          *");
//        System.out.println("*         [2]:用户登录          *");
//        System.out.println("*         [3]:用户注销          *");
//        System.out.println("*******************************");
//
//        int n = input.nextInt();
//        switch (n) {
//            case 1:
//                enroll(ctx);
//                break;
//            case 2:
//                login();
//                break;
//            case 3:
//                logout();
//                break;
//            default:
//                System.out.println("请按照要求输入哦！");
//                System.exit(0);
//        }
//
//    }
//
//    public void enroll(ChannelHandlerContext ctx){
//        System.out.println("请输入您的手机号码进行注册（6位）：");
//        int pn1 =input.nextInt();
//
//        ByteBuf byteBuf =ctx.alloc().buffer();
//        byteBuf.writeInt(pn1);
//        //byteBuf = (ByteBuf) msg;
//        ctx.writeAndFlush(byteBuf);
//
//        //去数据库中查找该手机号码注册过账号没有【规定，一个账号只能注册一个电话号码】
//        //注册过了：选择新的手机号进行注册
//        //没有注册过：继续进行登录
//        //检索数据库看这个用户的数据是否在数据库中
//
//        //调用服务层的函数完成
//
//
//    }
//    public void login(){
//
//    }
//    public void logout(){
//
//    }
//
//
//}
//
