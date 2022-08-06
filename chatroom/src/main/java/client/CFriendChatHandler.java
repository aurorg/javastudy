package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.FriendChatmsg;

import static client.ChatNettyClient.*;

public class CFriendChatHandler extends SimpleChannelInboundHandler<FriendChatmsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FriendChatmsg friendChatmsg) throws Exception {

        if (is1  && friendChatmsg.getUserid()==chatting) {

            System.out.println(friendChatmsg.getA() + friendChatmsg.getMessage());

            synchronized (waitMessage) {
                waitMessage.notifyAll();
            }
            return;
        }

        if (!unRead1) {
            System.out.println("您有未读好友消息");
            unRead1 = true;// 没有未读信息啦
        }
        synchronized (waitMessage) {
            waitMessage.notifyAll();
        }

//        if (is1 && friendChatmsg.getMessagetype().equals("FILE")) {
//           // isyes = true;
//            System.out.println("好友给你发文件，y:处理文件,n:不处理");
//
//
//                }

               // CFriendViewHandler.receiveFile();




        }
    }

