package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 主要包含了
 * 【1】用户注册账号界面
 * 【2】用户登录账号界面
 * 【3】用户注销账号界面
 * 完成上面这三个操作之后进行后续操作，就会调用MainView里面的界面
 */
public class CLoginViewHandler{

    //实现界面层和客户交流的代码
    public CLoginViewHandler(ChannelHandlerContext ctx){
        System.out.println("请输入密码");

    }

}
