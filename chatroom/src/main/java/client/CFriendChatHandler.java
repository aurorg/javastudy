package client;

import client.clienthandler.CFriendViewHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.FriendChatmsg;

import java.util.Scanner;

import static client.ChatNettyClient.*;
import static client.ChatNettyClient.waitMessage;

public class CFriendChatHandler extends SimpleChannelInboundHandler<FriendChatmsg>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FriendChatmsg friendChatmsg) throws Exception {

//        if(is1){
//
//            System.out.println(friendChatmsg.getMessage());
//
//            synchronized (waitMessage){
//                waitMessage.notifyAll();
//            }
//            return ;
//        }
//
//        if(!unRead){
//            System.out.println("您还有未读消息");
//            unRead=true;// 没有未读信息啦
//        }

        if(is1 && friendChatmsg.getUserid()==talker){

           // System.out.println(friendChatmsg.getMessage());
            String shu =String.format("\t%d : %s",friendChatmsg.getUserid(),friendChatmsg.getMessage());
            System.out.println(shu);
            if(friendChatmsg.getMessagetype().equals("FILE")){
                isyes=true;
                System.out.println("好友给你发文件，y:处理文件,n:不处理");
                new Thread(()->{
                    int count =1000;
                    while(!check.equalsIgnoreCase("y") && count >0){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        count--;
                    }
                    if(count >0){
                        CFriendViewHandler.receiveFile(shu,new Scanner(System.in),ctx,friendChatmsg.getUserid());
                        synchronized (waitfile){
                            waitfile.notifyAll();
                        }
                    }
                }).start();
            }
        }
        else{
            if(unRead==0 && is1==false){
                System.out.println("有未读消息");
            }
            int count =friendChatmsg.getCount();

            unRead+=count;
        }
    }
}
