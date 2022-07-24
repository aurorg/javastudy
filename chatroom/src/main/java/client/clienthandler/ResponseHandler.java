package client.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.Message;
import message.ServerToClientmsg;

public class ResponseHandler extends SimpleChannelInboundHandler<ServerToClientmsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ServerToClientmsg message) throws Exception {

        boolean success=message.getSuccess();
        String reason=message.getReason();
        int ResponseMessageType=message.getMessageType();

        if(!success){
            System.out.print("操作失败 "+reason);
//            waitSuccess=0;
        }else{
//            if(message.getMessageType()== Message.noticeMapMessage){
//                noticeMap=msg.getNoticeMap();
//                synchronized (waitMessage){
//                    waitMessage.notifyAll();
//                }
//                return;
//            }
            System.out.print("操作成功 "+reason);
//            waitSuccess=1;
        }
//        synchronized (waitMessage){
//            waitMessage.notifyAll();
//        }
    }
}
