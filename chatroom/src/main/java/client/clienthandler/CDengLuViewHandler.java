package client.clienthandler;


import io.netty.channel.ChannelHandlerContext;
import message.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

import static client.ChatNettyClient.waitMessage;
import static client.ChatNettyClient.waitSuccess;

/**
 * 主要包含了
 * 【1】用户注册账号界面
 * 【2】用户登录账号界面
 * 【3】用户注销账号界面
 * 完成上面这三个操作之后进行后续操作，就会调用MainView里面的界面
 */
//@Slf4j
public class CDengLuViewHandler {
    //用户输入
    static Scanner input = new Scanner(System.in);
    //实现界面层和客户交流的代码
    public CDengLuViewHandler(ChannelHandlerContext ctx){
        System.out.println("*******************************");
        System.out.println("*        欢迎来到用户界面        *");
        System.out.println("*       根据您的需求进行选择      *");
        System.out.println("*         [1]:用户注册          *");
        System.out.println("*         [2]:用户登录          *");
        System.out.println("*         [3]:用户注销          *");
        System.out.println("*         [4]:退出系统(下线）    *");
        System.out.println("*         [5]:找回密码          *");
        System.out.println("*         [6]:修改密码          *");
        System.out.println("*******************************");

       // int n = input.nextInt();
        String n=input.next();
        while(!StringUtils.isNumeric(n)){
            System.out.println("不合法输入，重新输入哦！");
            n =input.next();
        }

        //把n转成int类型
        switch (Integer.parseInt(n)) {
            case 1:
               enroll(ctx);
                break;
            case 2:
                login(ctx);
                break;
            case 3:
                logout(ctx);
                break;
            case 4:
//                System.out.println("退出啦！");
//                ctx.channel().close();
                offline(ctx);
                System.exit(0);
                break;
            case 5:
                findpassword(ctx);
                break;
            case 6:
                updatepassword(ctx);
                break;
            default:
                System.out.println("请按照要求输入哦！再给你一次重新输入的机会");
                new CDengLuViewHandler(ctx);
        }
    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //注册操作
    public void enroll(ChannelHandlerContext ctx){
        System.out.println("请输入您的手机号码进行注册（6位）：");
        String n =input.next();
        while(!StringUtils.isNumeric(n)){
            System.out.println("输入太随意，重新输入哦！");
            n=input.next();
        }
        int pn1=Integer.parseInt(n);

        //log.info("23432424");
        System.out.println("请输入您的账号昵称：");
        String name1 =input.next();

        System.out.println("请输入您的账号密码：");
        String psw1 =input.next();

        //向服务端将这些消息发过去
        Enrollmsg message = new Enrollmsg(pn1,name1,psw1);
        // message.setPhonenumber(pn1);
        ctx.writeAndFlush(message);

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
            System.out.println("注册成功，请选择接下来的操作\n");
            new CDengLuViewHandler(ctx);
        }
    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //登录操作
    public void login(ChannelHandlerContext ctx) {
        System.out.println("请输入您的账号【id】:");
       // int userid1 = input.nextInt();
        String n =input.next();
        while(!StringUtils.isNumeric(n)){
            System.out.println("输入太随意，重新输入哦！");
            n=input.next();
        }
        int userid1 =Integer.parseInt(n);

        System.out.println("请输入您的密码：");
        String psw1 = input.next();

        System.out.println("请再次确认密码：");
        String psw2 = input.next();

        /**
         * 将id号发给服务端，让服务端去数据库查找有没有这个账号，核对密码对不对
         */

        //向客户端将账号和id发过去
        Loginmsg loginmsg = new Loginmsg(userid1, psw1);
        ctx.writeAndFlush(loginmsg);

        //这里需要加锁，服务端返回消息后，客户端继续
        try {
            synchronized (waitMessage) {
                waitMessage.wait();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //如果两次密码相同就成功登录
        //不相同的话重新输入密码
        if (waitSuccess == 1) {

            if (psw1.equals(psw2)) {
                //登录成功
                System.out.println("登陆成功啦！");

                //登录成功之后就建立一个handler【为了后面发消息用，用map，键：用户的id,值：handler,对应起来】

                System.out.println("接下来，您可以根据需求选择功能哦！");

                //调用主要的功能函数
                new CMainViewHandler(ctx);
                //下一步
                //聊天功能+注销

            }
            //第一次输入的密码和数据库的一样，第二次输入的不一样
            else {
                System.out.println("\n");
                System.out.println("密码或者账号错误，请重新登录!!!");
                login(ctx); //密码错了的话再次调用登录函数继续登录
            }

        }else{
            System.out.println("密码或者账号错误，请重新登录!!!");
            login(ctx); //密码错了的话再次调用登录函数继续登录
        }

    }

//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //注销账号
    public void logout(ChannelHandlerContext ctx){
        System.out.println("用户注销操作如下：");
        System.out.println("请输入您的账号【id】:");
        int userid1 =input.nextInt();

        System.out.println("请输入您的密码：");
        String psw1 =input.next();

        System.out.println("请再次确认密码：");
        String psw2 =input.next();

        System.out.println("*请问您确定注销该账户了吗**");
        System.out.println("**********************");
        System.out.println("******[1]确定删除*******");
        System.out.println("******[2]不删除了*******");
        System.out.println("**不删除了就进行后续操作***");
        System.out.println("***********************");

        int n = input.nextInt();
        switch (n) {
            case 1://向服务器发消息，让服务器从数据库中删除这个账号

                //向服务器将账户的id发过去
                Logoutmsg logoutmsg =new Logoutmsg(userid1);
                ctx.writeAndFlush(logoutmsg);

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
                    System.out.println("注销账号成功\n");
                    //new CLoginViewHandler(ctx);
                }
                break;

            case 2:
                //不删除了就进行后面的操作
                new CDengLuViewHandler(ctx);
                break;

            default:
                System.out.println("请按照要求输入哦！");
                new CDengLuViewHandler(ctx);
        }
    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //下线处理
   public void offline(ChannelHandlerContext ctx){
       System.out.println("请输入您的账号【id】:");
       int userid1 = input.nextInt();

       OffLinemsg offLinemsg = new OffLinemsg(userid1);
       ctx.writeAndFlush(offLinemsg);

       try {
           synchronized (waitMessage) {
               waitMessage.wait();
           }

       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       System.out.println("正在为你关闭管道，即将下线");

       ctx.channel().close();

   }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //找回密码
    public void findpassword(ChannelHandlerContext ctx){

        System.out.println("请输入你的id号：");
        int userid=input.nextInt();
        System.out.println("请输入您的电话号码：");
        int phonenumber=input.nextInt();

        FindPasswordmsg findPasswordmsg =new FindPasswordmsg(userid,phonenumber);
        ctx.writeAndFlush(findPasswordmsg);
        try {
            synchronized (waitMessage) {
                waitMessage.wait();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
        System.out.println("找回成功");
        System.out.println("接下来为你返回主界面");
        new CDengLuViewHandler(ctx);

    }
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //修改密码
    public void updatepassword(ChannelHandlerContext ctx){
        System.out.println("请输入你的id号：");
        int userid=input.nextInt();
        System.out.println("请输入您的电话号码：");
        int phonenumber=input.nextInt();
        System.out.println("请输入你的原密码：");
         String ps= input.next();
        System.out.println("请输入您需要修改的密码：");
        String password=input.next();

        UpdatePasswordmsg updatePasswordmsg = new UpdatePasswordmsg(userid,phonenumber,password);
        ctx.writeAndFlush(updatePasswordmsg);
        try {
            synchronized (waitMessage) {
                waitMessage.wait();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
        System.out.println("修改成功");
        System.out.println("接下来为你返回主界面");
        new CDengLuViewHandler(ctx);
    }

}
