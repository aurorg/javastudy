package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;

import java.util.Scanner;

public class CGroupThreeViewHandler {
    //用户输入
    static Scanner input = new Scanner(System.in);

    public CGroupThreeViewHandler(ChannelHandlerContext ctx) {

        System.out.println("*******************************");
        System.out.println("*      欢迎来到普通用户界面       *");
        System.out.println("*      根据您的需求进行选择       *");
        System.out.println("*         [1]:创建群           *");
        System.out.println("*         [2]:申请加群          *");
        System.out.println("*         [3]:申请退群          *");
        System.out.println("*         [4]:查看群列表成员     *");
        System.out.println("*         [5]:查看群历史消息     *");
        System.out.println("*         [6]:查看群未读消息     *");
        System.out.println("*         [7]:群聊天           *");
        System.out.println("*         [8]:群通知处理       *");
        System.out.println("*         [0]:返回上一个界面     *");
        System.out.println("*******************************");

        int n = input.nextInt();
        switch (n) {
            case 1:
                threecase1(ctx);
                break;
            case 2:
                threecase2(ctx);
                break;
            case 3:
                threecase3(ctx);
                break;
            case 4:
                threecase4(ctx);
                break;
            case 5:
                threecase5(ctx);
                break;
            case 6:
                threecase6(ctx);
                break;
            case 7:
                threecase7(ctx);
                break;
            case 8:
                threecase8(ctx);
                break;
            case 0:
                //返回上一个界面
                new CGroupViewHandler(ctx);
                break;
            default:
                System.out.println("请按照要求输入哦！");
                new CGroupThreeViewHandler(ctx);

        }
    }

    //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void threecase1(ChannelHandlerContext ctx) {

    }

    //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void threecase2(ChannelHandlerContext ctx) {

    }

    //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void threecase3(ChannelHandlerContext ctx) {

    }

    //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void threecase4(ChannelHandlerContext ctx) {

    }

    //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void threecase5(ChannelHandlerContext ctx) {

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void threecase6(ChannelHandlerContext ctx) {

    }

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void threecase7(ChannelHandlerContext ctx) {

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void threecase8(ChannelHandlerContext ctx) {

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

}