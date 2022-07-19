package java3netty.temp.temp1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws Exception{

        //客户端需要一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            //创建客户端启动对象
            //注意客户端使用的不是ServerBootstrap,而是Bootstrap
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) //设置客户端通道的实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler()); //加入自己的处理器
                        }
                    });

            System.out.println("客户端 ok.....");

            //启动客户端取连接服务端
            //关于ChannelFuture需要分析：涉及netty的异步模型
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6686).sync();

            //给关闭通道增加一个连接进行监听
            channelFuture.channel().closeFuture().sync();
        }finally{
            group.shutdownGracefully();
        }
    }
}
