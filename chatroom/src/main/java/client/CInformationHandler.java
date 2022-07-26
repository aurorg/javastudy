package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.Informationmsg;

import static client.ChatNettyClient.waitMessage;

public class CInformationHandler extends SimpleChannelInboundHandler<Informationmsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext  ctx, Informationmsg informationmsg) throws Exception {

        int userid2=informationmsg.getUserid();
        int friendid2=informationmsg.getFriendid();
        String message2=informationmsg.getMessage();

        System.out.println( "发送者id：" +userid2 + ",接受者id：" + friendid2 + " ,消息是： " + message2);

        synchronized (waitMessage){
            waitMessage.notifyAll();
        }
    }
}
