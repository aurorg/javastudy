package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import message.*;

import java.util.Scanner;

import static client.ChatNettyClient.*;

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
//        System.out.println("*         [11]:批准用户加群     *");
        System.out.println("*         [11]:将用户踢出群     *");
        System.out.println("*         [12]:开启禁言模式     *");
        System.out.println("*         [13]:解除禁言模式     *");
        System.out.println("*         [14]:群通知申请处理    *");
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
//                onecase14(ctx);
//                break;
                //查看群通知、申请加群的处理
                new CPassAddGroupApplyView(ctx);
                break;
//            case 15:
//               // onecase15(ctx);
//                //查看群通知、申请加群的处理
//                new CPassAddGroupApplyView(ctx);
//                break;
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

        System.out.println("请输入您的id号：");
        int userid=input.nextInt();

        System.out.println("请输入您需要建群的群名称(和您之间建过的群的名称不能重复)：");
        String groupname=input.next();

        GroupSetupMessage groupSetupMessage = new GroupSetupMessage(userid,groupname);
        ctx.writeAndFlush(groupSetupMessage);
        //这里需要加锁，服务端返回消息后，客户端继续
        try {
            synchronized (waitMessage){
                waitMessage.wait();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("111111");

        if(waitSuccess==1){
            System.out.println("\n");
            System.out.println("建群成功，请选择接下来的操作\n");
            new CGroupOneViewHandler(ctx);
        }


    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //解散群
    public void onecase2(ChannelHandlerContext ctx){

        System.out.println("请输入您的id号：");
        int userid=input.nextInt();

        System.out.println("请输入您需要解散群的群账号id：");
        int groupid=input.nextInt();

        System.out.println("***请问您确定解散群了吗***");
        System.out.println("**********************");
        System.out.println("******[1]确定解散*******");
        System.out.println("******[2]不解散了*******");
        System.out.println("**不解散了就进行后续操作***");
        System.out.println("***********************");
        int n = input.nextInt();

        switch(n){
            case 1:
                 GroupDeleteMessage groupDeleteMessage=new GroupDeleteMessage(userid,groupid);
                 ctx.writeAndFlush(groupDeleteMessage);

                //这里需要加锁，服务端返回消息后，客户端继续【线程通知！！！！！】
                try {
                    synchronized (waitMessage){
                        waitMessage.wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //System.out.println("111111");
                if(waitSuccess==1){
                    System.out.println("\n");
                    System.out.println("解散群成功\n");
                    System.out.println("接下来你可以根据您的需求选择其他功能：");
                    new CGroupOneViewHandler(ctx);
                }
                break;

            case 2:

                new CGroupOneViewHandler(ctx);
                break;

            default:
                System.out.println("请按照要求输入哦！");
                new CGroupOneViewHandler(ctx);
        }

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //申请加群

   public void onecase3(ChannelHandlerContext ctx){
       System.out.println("请输入您的id号：");
       int userid=input.nextInt();

       System.out.println("请输入您需要加群的id号(确保该群存在)：");
       int groupid=input.nextInt();

       GroupSendApplyMessage groupSendApplyMessage=new GroupSendApplyMessage(userid,groupid,"请求加群");
       ctx.writeAndFlush(groupSendApplyMessage);
       try{
           synchronized(waitMessage){
               waitMessage.wait();
           }
       }catch (InterruptedException e){
           e.printStackTrace();
       }
       System.out.println("接下来为你返回主界面,您根据需求选择:");
       new CGroupOneViewHandler(ctx);


  }

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //申请退群
   public void onecase4(ChannelHandlerContext ctx){

       System.out.println("请输入您的id号：");
       int userid=input.nextInt();

       System.out.println("请输入您需要退出群的群账号id(确保该群存在)：");
       int groupid=input.nextInt();

       GroupQuitMessage groupQuitMessage= new GroupQuitMessage(userid,groupid,"退群的消息");
       ctx.writeAndFlush(groupQuitMessage);

       try{
           synchronized(waitMessage){
               waitMessage.wait();
           }
       }catch (InterruptedException e){
           e.printStackTrace();
       }
       System.out.println("接下来为你返回主界面,您根据需求选择:");
       new CGroupOneViewHandler(ctx);


   }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
   //查看群列表成员

   public void onecase5(ChannelHandlerContext ctx){
       System.out.println("请输入您的id号：");
       int userid= input.nextInt();
       System.out.println("请输入您需要查看群列表的该群的id号：");
       int groupid= input.nextInt();

       GroupListMemberMessage groupListMemberMessage = new GroupListMemberMessage(userid,groupid);
       ctx.writeAndFlush(groupListMemberMessage);
       try{
           synchronized(waitMessage){
               waitMessage.wait();
           }
       }catch (InterruptedException e){
           e.printStackTrace();
       }

       for (String s1 : grouplist) {
           System.out.println(s1);
       }

       System.out.println("\n");
       System.out.println("群成员列表如上文所示!");
       System.out.println("接下来为你返回群的主界面：");
       new CGroupOneViewHandler(ctx);

   }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //群历史消息
    public void onecase6(ChannelHandlerContext ctx){
        System.out.println("请输入您的id号：");
        int userid= input.nextInt();
        System.out.println("请输入您需要查看群历史消息的该群的id号：");
        int groupid= input.nextInt();

        GroupHistoryMessage groupHistoryMessage = new GroupHistoryMessage(userid,groupid);
        ctx.writeAndFlush(groupHistoryMessage);

        try {
            synchronized (waitMessage) {
                waitMessage.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //输出消息列表
        for(String s1 :grouphistorymsg){
            System.out.println(s1);
        }

        System.out.println("\n");
        System.out.println("群历史列表上文所示!");
        System.out.println("接下来为你返回群的主界面：");
        new CGroupOneViewHandler(ctx);

    }

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //查看群未读消息
    public void onecase7(ChannelHandlerContext ctx){

        System.out.println("请输入您的id号：");
        int userid= input.nextInt();
        System.out.println("请输入您需要查看群未读消息的该群的id号：");
        int groupid= input.nextInt();

        GroupUnreadMessage groupUnreadMessage = new GroupUnreadMessage(userid,groupid);
        ctx.writeAndFlush(groupUnreadMessage);

        try {
            synchronized (waitMessage) {
                waitMessage.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //输出消息列表
        for(String s1 :groupunreadmsg){
            System.out.println(s1);
        }

        System.out.println("\n");
        System.out.println("群未读列表上文所示!");
        System.out.println("接下来为你返回群的主界面：");
        new CGroupOneViewHandler(ctx);

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //群聊
    public void onecase8(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //添加管理员
    public void onecase9(ChannelHandlerContext ctx){
        System.out.println("请输入您的id号：");
        int userid=input.nextInt();
        System.out.println("请输入你需要添加管理员的群id号：");
        int groupid=input.nextInt();
        System.out.println("请输入你将哪位用户添加为管理员的id号：");
        int peopleid=input.nextInt();

        GroupAddAdministratorMessage groupAddAdministratorMessage=new GroupAddAdministratorMessage(userid,groupid,peopleid);
        ctx.writeAndFlush(groupAddAdministratorMessage);
        try{
            synchronized(waitMessage){
                waitMessage.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("\n");
        System.out.println("管理员已经被您添加！！");
        System.out.println("接下来为你返回群的主界面：");
        new CGroupOneViewHandler(ctx);

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //删除管理员（其实就是将管理员的身份改为普通用户）
    public void onecase10(ChannelHandlerContext ctx){
        System.out.println("请输入您的id号：");
        int userid=input.nextInt();
        System.out.println("请输入你需要删除管理员的群id号：");
        int groupid=input.nextInt();
        System.out.println("请输入你将哪位用户删除管理员身份的id号：");
        int peopleid=input.nextInt();

        GroupDeleteAdministratorMessage groupDeleteAdministratorMessage = new GroupDeleteAdministratorMessage(userid,groupid,peopleid);
        ctx.writeAndFlush(groupDeleteAdministratorMessage);

        try{
            synchronized(waitMessage){
                waitMessage.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("\n");
        System.out.println("管理员已经被您删除！！");
        System.out.println("接下来为你返回群的主界面：");
        new CGroupOneViewHandler(ctx);

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase11(ChannelHandlerContext ctx){

            System.out.println("请输入您的id号：");
            int userid=input.nextInt();
            System.out.println("请输入你需要删除用户的群id号：");
            int groupid=input.nextInt();
            System.out.println("请输入你删除用户的id号：");
            int peopleid=input.nextInt();

            GroupDeleteMemberMessage groupDeleteMemberMessage = new GroupDeleteMemberMessage(userid,groupid,peopleid);
            ctx.writeAndFlush(groupDeleteMemberMessage);

        try{
            synchronized(waitMessage){
                waitMessage.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("\n");
        System.out.println("该用户已经被您踢出群！！");
        System.out.println("接下来为你返回群的主界面：");
        new CGroupOneViewHandler(ctx);

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase12(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    public void onecase13(ChannelHandlerContext ctx){

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
//
//    public void onecase14(ChannelHandlerContext ctx){
//
//    }
////——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

//    public void onecase15(ChannelHandlerContext ctx){
//
//    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————



}
