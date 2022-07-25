package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;

import java.util.Scanner;

public class CFriendViewHandler {
    //用户输入
    static Scanner input = new Scanner(System.in);

    public CFriendViewHandler(ChannelHandlerContext ctx){
        System.out.println("*******************************");
        System.out.println("*        欢迎来到好友界面        *");
        System.out.println("*       根据您的需求进行选择      *");
        System.out.println("*         [1]:添加好友          *");
        System.out.println("*         [2]:删除好友          *");
        System.out.println("*         [3]:好友列表          *");
        System.out.println("*         [4]:查询好友          *");
        System.out.println("*         [5]:屏蔽好友          *");
        System.out.println("*         [6]:好友聊天          *");
        System.out.println("*         [0]:返回主界面         *");
        System.out.println("*******************************");

        int n = input.nextInt();
        switch (n){
            case 1:
                //添加好友
                addfriend(ctx);
                break;
            case 2:
                //删除好友
                deletefriend(ctx);
                break;
            case 3:
                //好友列表
                friendlist(ctx);
                break;
            case 4:
                //查询好友
                findfriend(ctx);
                break;
            case 5:
                //屏蔽好友
                shieldfriend(ctx);
                break;
            case 6:
                //好友聊天
                friendlist(ctx);
                break;
            case 0:
                //返回主界面
                new CMainViewHandler(ctx);
                break;

            default:
                System.out.println("请按照要求输入哦！");
                System.exit(0);

        }
    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //1  添加好友
    public void addfriend(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //2  删除好友
    public void deletefriend(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //3  好友列表
    public void friendlist(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //4  查询好友
    public void findfriend(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //5  屏蔽好友
    public void shieldfriend(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //6  好友聊天

    /**
     * 1、输入好友的id账号
     * 2、发消息给服务端告诉好友的id号，
     * 3、服务器去查找是否好友，是否被屏蔽，是否离线，发消息告诉给客户端
     * 4、【①不是好友，被屏蔽了满足其一】都不能发消息（返回界面）
     *   【②离线的话可以发消息存到数据库中，好友收不到】（发完返回界面层）
     *   【③是好友，没有被屏蔽，没有离线】就可以开始聊天
     *
     */
    public void friendchat(ChannelHandlerContext ctx){
        System.out.println("请输入您的id：");
        int id1=input.nextInt();

        System.out.println("请输入你需要发消息的好友id:");
        int friendid1 = input.nextInt();


    }


}
