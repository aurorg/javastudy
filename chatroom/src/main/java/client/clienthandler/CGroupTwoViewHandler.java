package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;

import java.util.Scanner;

public class CGroupTwoViewHandler {

    //用户输入
    static Scanner input = new Scanner(System.in);

    public CGroupTwoViewHandler(ChannelHandlerContext ctx) {
        System.out.println("*******************************");
        System.out.println("*       欢迎来到群管理员界面      *");
        System.out.println("*       根据您的需求进行选择      *");
        System.out.println("*         [1]:创建群           *");
        System.out.println("*         [2]:申请加群          *");
        System.out.println("*         [3]:申请退群          *");
        System.out.println("*         [4]:查看群列表成员     *");
        System.out.println("*         [5]:查看群历史消息     *");
        System.out.println("*         [6]:查看群未读消息     *");
        System.out.println("*         [7]:群聊天           *");
        System.out.println("*         [8]:批准用户加群      *");
        System.out.println("*         [9]:将用户踢出群      *");
        System.out.println("*         [10]:开启禁言模式     *");
        System.out.println("*         [11]:解除禁言模式     *");
        System.out.println("*         [12]:群通知处理       *");
        System.out.println("*         [0]:返回上一个界面     *");
        System.out.println("*******************************");

        int n = input.nextInt();
        switch (n) {
            case 1:
                onecase1(ctx);
                break;
            case 2:
                twocase2(ctx);
                break;
            case 3:
                twocase3(ctx);
                break;
            case 4:
                twocase4(ctx);
                break;
            case 5:
                twocase5(ctx);
                break;
            case 6:
                twocase6(ctx);
                break;
            case 7:
                twocase7(ctx);
                break;
            case 8:
                twocase8(ctx);
                break;
            case 9:
                twocase9(ctx);
                break;
            case 10:
                twocase10(ctx);
                break;
            case 11:
                twocase11(ctx);
                break;
            case 12:
                twocase12(ctx);
                break;
            case 0:
                //返回上一个界面
                new CGroupViewHandler(ctx);
                break;
            default:
                System.out.println("请按照要求输入哦！");
                new CGroupTwoViewHandler(ctx);
        }
    }

    //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void twocase1(ChannelHandlerContext ctx) {

    }

    //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void twocase2(ChannelHandlerContext ctx) {

    }

    //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void twocase3(ChannelHandlerContext ctx) {

    }

    //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void twocase4(ChannelHandlerContext ctx) {

    }

    //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void twocase5(ChannelHandlerContext ctx) {

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void twocase6(ChannelHandlerContext ctx) {

    }

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void twocase7(ChannelHandlerContext ctx) {

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void twocase8(ChannelHandlerContext ctx) {

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void twocase9(ChannelHandlerContext ctx) {

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void twocase10(ChannelHandlerContext ctx) {

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void twocase11(ChannelHandlerContext ctx) {

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void twocase12(ChannelHandlerContext ctx) {

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
}

