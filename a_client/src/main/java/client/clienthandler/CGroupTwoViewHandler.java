package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

import static client.clienthandler.CGroupOneViewHandler.*;

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
//        System.out.println("*         [8]:批准用户加群      *");
        System.out.println("*         [8]:将用户踢出群      *");
        System.out.println("*         [9]:开启禁言模式     *");
        System.out.println("*         [10]:解除禁言模式     *");
        System.out.println("*         [11]:群通知处理       *");
        System.out.println("*         [0]:返回上一个界面     *");
        System.out.println("*******************************");

       // int n = input.nextInt();
        String n=input.next();
        while(!StringUtils.isNumeric(n)){
            System.out.println("不合法输入，重新输入哦！");
            n =input.next();
        }
        switch (Integer.parseInt(n)) {
            case 1:
               onecase1(ctx);
                break;
            case 2:
                onecase3(ctx);
                break;
            case 3:
                onecase4(ctx);
                break;
            case 4:
                onecase5(ctx);
                break;
            case 5:
                onecase6(ctx);
                break;
            case 6:
                onecase7(ctx);
                break;
            case 7:
                onecase8(ctx);
                break;
            case 8:
                onecase11(ctx);
                break;
            case 9:
                onecase12(ctx);
                break;
            case 10:
                onecase13(ctx);
                break;
            case 11:
                new CPassAddGroupApplyView(ctx);
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


}

