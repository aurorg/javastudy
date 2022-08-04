package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.GroupChatMessage;

import static client.ChatNettyClient.*;
import static client.ChatNettyClient.waitMessage;

public class CGroupChatHandler extends SimpleChannelInboundHandler<GroupChatMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatMessage groupChatMessage) throws Exception {

        if (is2) {

            //将消息打印出来
            System.out.println(groupChatMessage.getA() + groupChatMessage.getMessage());

            synchronized (waitMessage) {
                waitMessage.notifyAll();
            }
            return;
        }

        if (!unRead2) {
            System.out.println("您有未读群消息");
            unRead2 = true;// 没有未读信息啦
        }
        synchronized (waitMessage) {
            waitMessage.notifyAll();
        }


    }
}
