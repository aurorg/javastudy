package server.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import message.GroupGetFilemsg;

public class SGroupGetFileHandler extends SimpleChannelInboundHandler<GroupGetFilemsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupGetFilemsg groupGetFilemsg) throws Exception {

    }
}
