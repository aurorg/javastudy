package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.GroupSendApplyMessage;

public class SGroupSendApplyHandler extends SimpleChannelInboundHandler<GroupSendApplyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupSendApplyMessage groupSendApplyMessage) throws Exception {

    }
}
