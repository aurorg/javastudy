package client;

import client.clienthandler.CFriendViewHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.FriendChatmsg;

import java.util.Scanner;

import static client.ChatNettyClient.*;
import static client.ChatNettyClient.waitMessage;

public class CFriendChatHandler extends SimpleChannelInboundHandler<FriendChatmsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FriendChatmsg friendChatmsg) throws Exception {

        if (is1) {

            System.out.println(friendChatmsg.getMessage());

            synchronized (waitMessage) {
                waitMessage.notifyAll();
            }
            return;
        }

        if (!unRead) {
            System.out.println("您还有未读消息");
            unRead = true;// 没有未读信息啦
        }
        synchronized (waitMessage) {
            waitMessage.notifyAll();
        }

        if (is1 && friendChatmsg.getMessagetype().equals("FILE")) {
            isyes = true;
            System.out.println("好友给你发文件，y:处理文件,n:不处理");
            new Thread(() -> {
                while (!check.equalsIgnoreCase("y")) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

               // CFriendViewHandler.receiveFile();

                synchronized (waitfile) {
                    waitfile.notifyAll();
                }

            }).start();

        }
    }
}
