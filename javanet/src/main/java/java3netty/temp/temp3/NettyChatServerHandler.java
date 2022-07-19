package java3netty.temp.temp3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

public class NettyChatServerHandler extends SimpleChannelInboundHandler<String>{
    public static List<Channel>  channelList = new ArrayList<>();
    
    //管道就绪事件
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
         Channel channel =ctx.channel();

         //当有新的客户端连接的时候，将通道放入集合
        channelList.add(channel);
        System.out.println("[Server] :" + channel.remoteAddress().toString().substring(1) + "在线。");
    }


}
