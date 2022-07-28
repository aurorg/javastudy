package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.Message;
import message.ServerToClientmsg;

import static client.ChatNettyClient.*;

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
        //成功里面分好多种情况

        else{
            waitSuccess=1;
//            if(message.getMessageType()== Message.Informationmsg){
//                informationMap=message.getInformationMap();
//
//            }
//            else if(message.getMessageType()==)
            if(message.getMessageType()==Message.Informationfriendhistorymsg){
                friendmsglist=message.friendmsglist;
            }

            else if (message.getMessageType()==Message.Informationfriendunreadmsg){
                friendmsglist1=message.friendmsglist1;
            }
            else if(message.getMessageType()==Message.FriendListmsg){
                friendlist=message.friendlist;
            }


            else {
                System.out.print("操作成功 " + reason);
                waitSuccess = 1;

                synchronized (waitMessage){
                    waitMessage.notifyAll();
                }

            }
        }

        //不管操作成功还是失败都需要唤醒界面的主线程
        synchronized (waitMessage){
            waitMessage.notifyAll();
        }
    }
}
