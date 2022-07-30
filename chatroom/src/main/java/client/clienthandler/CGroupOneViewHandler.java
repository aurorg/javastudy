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
                case1(ctx);
                break;
            case 2:
                case2(ctx);
                break;
            case 3:
                case3(ctx);
                break;
            case 4:
                case4(ctx);
                break;
            case 5:
                case5(ctx);
                break;
            case 6:
                case6(ctx);
                break;
            case 7:
                case7(ctx);
                break;
            case 8:
                case8(ctx);
                break;
            case 9:
                case9(ctx);
                break;
            case 10:
                case10(ctx);
                break;
            case 11:
                case11(ctx);
                break;
            case 12:
                case12(ctx);
                break;
            case 13:
                case13(ctx);
                break;
            case 14:
                case14(ctx);
                break;
            case 15:
                case15(ctx);
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
    public void case1(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void case2(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
   public void case3(ChannelHandlerContext ctx){

  }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
   public void case4(ChannelHandlerContext ctx){

   }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
   public void case5(ChannelHandlerContext ctx){

   }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void case6(ChannelHandlerContext ctx){

    }

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void case7(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void case8(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void case9(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void case10(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void case11(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void case12(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void case13(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void case14(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void case15(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————



}
