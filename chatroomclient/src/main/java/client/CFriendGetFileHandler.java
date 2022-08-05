package client;

//暂时没有用

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.FriendGetFilemsg;

import static client.ChatNettyClient.*;

public class CFriendGetFileHandler extends SimpleChannelInboundHandler<FriendGetFilemsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FriendGetFilemsg friendGetFilemsg) throws Exception {
        if(is1){
            System.out.println(friendGetFilemsg.getMessage());

            synchronized (waitMessage) {
                waitMessage.notifyAll();
            }
            return;
        }

        if (!unRead1) {
            System.out.println("您还有未读消息");
            unRead1 = true;// 没有未读文件信息啦
        }

        synchronized (waitMessage) {
            waitMessage.notifyAll();
        }


    }
}
