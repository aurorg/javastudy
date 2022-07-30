package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;

import java.util.Scanner;

public class CGroupViewHandler {
    //用户输入
    static Scanner input = new Scanner(System.in);

    public CGroupViewHandler(ChannelHandlerContext ctx){

        System.out.println("请输入你需要访问的群id:");
        int groupid=input.nextInt();

        System.out.println("请输入你在这个群里的身份");
        System.out.println("********************************");
        System.out.println("*         欢迎来到群界面！         *");
        System.out.println("*       根据您的身份进行选择！      *");
        System.out.println("*        [1]:群主               *");
        System.out.println("*        [2]:群管理员            *");
        System.out.println("*        [3]:普通用户            *");
        System.out.println("*        [0]:返回主界面          *");
        System.out.println("********************************");

        //给服务器发消息，服务器从数据库查看数据，确认你的身份是否正确

        //确认了之后进行下一步选择

        int n=input.nextInt();
        switch(n){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 0:
                System.out.println("接下来即将为你返回主界面！");
                new CMainViewHandler(ctx);
                break;
            default:
                System.out.println("请按照要求输入哦，再 给你一次机会");
                new CGroupViewHandler(ctx);

        }
    }
}
