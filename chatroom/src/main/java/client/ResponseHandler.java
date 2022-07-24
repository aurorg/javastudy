package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.Message;
import message.ServerToClientmsg;
import client.clienthandler.CLoginViewHandler;

import static client.ChatNettyClient.waitMessage;
import static client.ChatNettyClient.waitSuccess;

public class ResponseHandler extends SimpleChannelInboundHandler<ServerToClientmsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ServerToClientmsg message) throws Exception {

        boolean success=message.getSuccess();
        String reason=message.getReason();
        int ResponseMessageType=message.getMessageType();

        if(!success){
            System.out.print("操作失败 "+reason);
            waitSuccess=0;
        }
        else{
               //return;
//           }
            System.out.print("操作成功 "+reason);
            waitSuccess=1;
        }
        synchronized (waitMessage){
            waitMessage.notifyAll();
        }
    }
}
