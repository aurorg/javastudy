package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.FriendChatmsg;

import static client.ChatNettyClient.*;
import static client.ChatNettyClient.waitMessage;

public class CFriendChatHandler extends SimpleChannelInboundHandler<FriendChatmsg>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FriendChatmsg friendChatmsg) throws Exception {
        if(is1){

            System.out.println(friendChatmsg.getMessage());

            synchronized (waitMessage){
                waitMessage.notifyAll();
            }
            return ;
        }

        if(!unRead){
            System.out.println("您还有未读消息");
            unRead=true;// 没有未读信息啦
        }

    }
}
