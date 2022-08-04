package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import message.GroupJoinMessage;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

import static client.ChatNettyClient.waitMessage;

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
        System.out.println("*       [0]:返回群界面           *");
        System.out.println("*******************************");

        //int n=input.nextInt();
        String n=input.next();
        while(!StringUtils.isNumeric(n)){
            System.out.println("不合法输入，重新输入哦！");
            n =input.next();
        }
        switch(Integer.parseInt(n)){
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
        System.out.println("请输入您的id号：");
        int userid1=input.nextInt();
        System.out.println("请输入你需要处理群通知的该群的id号：");
        int groupid1=input.nextInt();
        System.out.println("请输入需要加您的群的用户的id号：");
        int peopleid1=input.nextInt();

        GroupJoinMessage groupJoinMessage=new GroupJoinMessage(userid1,groupid1,peopleid1);
        ctx.writeAndFlush(groupJoinMessage);
        try{
            synchronized(waitMessage){
                waitMessage.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("\n");
        System.out.println("申请已经通过，用户已经进群啦！");
        System.out.println("接下来为你返回群主界面：");
        new CGroupOneViewHandler(ctx);
    }

}
