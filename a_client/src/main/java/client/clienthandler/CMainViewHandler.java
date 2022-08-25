package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 *
 *
 */
public class CMainViewHandler {
    //用户输入
    static Scanner input = new Scanner(System.in);

    public CMainViewHandler(ChannelHandlerContext ctx){
        //System.out.println("reiulhfglihgvh111111");
        System.out.println("********************************");
        System.out.println("*         欢迎来到主界面！        *");
        System.out.println("*       根据您的需求进行选择！     *");
        System.out.println("*        [1]:查看消息界面        *");
        System.out.println("*        [2]:查看好友界面        *");
        System.out.println("*        [3]:查看群组界面        *");
        System.out.println("*        [4]:返回用户界面        *");
        System.out.println("*******************************");

        //int n = input.nextInt();
        String n=input.next();
        while(!StringUtils.isNumeric(n)){
            System.out.println("不合法输入，重新输入哦！");
            n =input.next();
        }
        switch(Integer.parseInt(n)){
            case 1:
                //查看消息界面
                new CInformationViewHandler(ctx);
                break;
            case 2:
                //好友界面
                new CFriendViewHandler(ctx);
                break;
            case 3:
                //群界面
                new CGroupViewHandler(ctx);
                break;
            case 4:
                //返回上一层，也就是用户界面
                new CDengLuViewHandler(ctx);
            default:
                System.out.println("请按照要求输入哦!再给你一次重新输入的机会");
                new CMainViewHandler(ctx);
        }
    }
}
