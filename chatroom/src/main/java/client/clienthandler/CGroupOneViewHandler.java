package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;

import java.util.Scanner;

public class CGroupOneViewHandler {
    //用户输入
    static Scanner input = new Scanner(System.in);

    public CGroupOneViewHandler(ChannelHandlerContext ctx){
        System.out.println("*******************************");
        System.out.println("*        欢迎来到群主界面        *");
        System.out.println("*       根据您的需求进行选择      *");
        System.out.println("*         [1]:创建群           *");
        System.out.println("*         [2]:解散群           *");
        System.out.println("*         [3]:申请加群          *");
        System.out.println("*         [4]:申请退群          *");
        System.out.println("*         [5]:查看群列表成员     *");
        System.out.println("*         [6]:查看群历史消息     *");
        System.out.println("*         [7]:查看群未读消息     *");
        System.out.println("*         [8]:群聊天           *");
        System.out.println("*         [9]:添加管理员        *");
        System.out.println("*         [10]:删除管理员       *");
        System.out.println("*         [11]:批准用户加群     *");
        System.out.println("*         [12]:将用户踢出群     *");
        System.out.println("*         [13]:开启禁言模式     *");
        System.out.println("*         [14]:解除禁言模式     *");
        System.out.println("*         [15]:群通知处理       *");
        System.out.println("*         [0]:返回上一个界面     *");
        System.out.println("*******************************");

        int n =input.nextInt();
        switch(n){
            case 1:
                onecase1(ctx);
                break;
            case 2:
                onecase2(ctx);
                break;
            case 3:
                onecase3(ctx);
                break;
            case 4:
                onecase4(ctx);
                break;
            case 5:
                onecase5(ctx);
                break;
            case 6:
                onecase6(ctx);
                break;
            case 7:
                onecase7(ctx);
                break;
            case 8:
                onecase8(ctx);
                break;
            case 9:
                onecase9(ctx);
                break;
            case 10:
                onecase10(ctx);
                break;
            case 11:
                onecase11(ctx);
                break;
            case 12:
                onecase12(ctx);
                break;
            case 13:
                onecase13(ctx);
                break;
            case 14:
                onecase14(ctx);
                break;
            case 15:
                onecase15(ctx);
                break;
            case 0:
                //返回上一个界面
                new CGroupViewHandler(ctx);
                break;
            default:
                System.out.println("请按照要求输入哦！");
                new CGroupOneViewHandler(ctx);

        }

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //创建群
    public void onecase1(ChannelHandlerContext ctx){
        




















    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void onecase2(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
   public void onecase3(ChannelHandlerContext ctx){

  }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
   public void onecase4(ChannelHandlerContext ctx){

   }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
   public void onecase5(ChannelHandlerContext ctx){

   }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase6(ChannelHandlerContext ctx){

    }

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase7(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase8(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase9(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase10(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase11(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase12(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase13(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase14(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase15(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————



}
