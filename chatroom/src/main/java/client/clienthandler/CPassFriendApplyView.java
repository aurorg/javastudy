package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import message.Addfriendmsg;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

import static client.ChatNettyClient.waitMessage;

public class CPassFriendApplyView {
    //用户输入
    static Scanner input = new Scanner(System.in);

    public CPassFriendApplyView(ChannelHandlerContext ctx) {
        System.out.println("*******************************");
        System.out.println("*       欢迎来到添加好友界面       *");
        System.out.println("*      根据您的需求进行选择        *");
        System.out.println("*       [1]:同意好友请求         *");
        System.out.println("*       [2]:拒绝好友请求         *");
        System.out.println("*       [3]:暂时不处理           *");
        System.out.println("*       [0]:返回好友界面         *");
        System.out.println("*******************************");

        //int n = input.nextInt();
        String n=input.next();
        while(!StringUtils.isNumeric(n)){
            System.out.println("不合法输入，重新输入哦！");
            n =input.next();
        }
        switch (Integer.parseInt(n)) {
            case 1:
                passfriend(ctx);
                break;
            case 2:
                System.out.println("不通过好友申请成功，接下来给你返回好友界面");
                new CFriendViewHandler(ctx);
                break;
            case 3:
                System.out.println("暂时不处理就退出啦！");
                new CFriendViewHandler(ctx);
                break;
            case 0:
                new CFriendViewHandler(ctx);
                break;

            default:
                System.out.println("请按照要求输入哦！再给你一次重新输入的机会");
                new CPassFriendApplyView(ctx);

        }
    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void  passfriend(ChannelHandlerContext ctx){
        System.out.println("请输入您的账号id:");
        int userid1 =input.nextInt();
        System.out.println("请输入你需要查看好友申请的好友账号id：");
        int friendid1 = input.nextInt();

        Addfriendmsg addfriendmsg =new Addfriendmsg(userid1,friendid1);
        ctx.writeAndFlush(addfriendmsg);
        try{
            synchronized(waitMessage){
                waitMessage.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("申请已经通过，你们已经成为好友啦！");
        System.out.println("接下来返回好友界面，您可以根据您的需要选择功能");
        new CFriendViewHandler(ctx);


    }

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
//


}
