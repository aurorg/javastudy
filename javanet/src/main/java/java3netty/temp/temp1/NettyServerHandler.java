package java3netty.temp.temp1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

//说明：
//1.自定义一个handler，需要继承netty规定好的某个HandlerAdapter（适配器）
//2.这个时候我们自定义的一个Handler,才能称为一个handler
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据实际（可以读取客户端发送的消息）
    //1.ChannelHandlerContext ctx :上下文对象，含有的信息有：管道pipeline，通道channel，地址
    //2. Object msg ：客户端发送的数据，默认是onject
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        System.out.println("server ctx =" + ctx);

        //
    }
}
