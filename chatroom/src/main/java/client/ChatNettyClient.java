package client;

import client.clienthandler.CLoginViewHandler;
import client.clienthandler.ResponseHandler;
import common.MessageCodec;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import message.ServerToClientmsg;

public class ChatNettyClient {
    public static void main(String[] args) throws Exception{

        //客户端需要一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();

        LoggingHandler Log=new LoggingHandler(LogLevel.DEBUG);
      //  MessageCodec clientCodec=new MessageCodec();



        try {
            //创建客户端启动对象
            //注意客户端使用的不是ServerBootstrap,而是Bootstrap
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) //设置客户端通道的实现类
                    .handler( new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MessageCodec());
                            ch.pipeline().addLast(new ResponseHandler());
                            //ch.pipeline().addLast(new ServerToClientmsg());
                            ch.pipeline().addLast("CLoginViewHandler",new ChannelInboundHandlerAdapter(){  //加入自己的处理器,需要什么处理器加什么处理器（这个后面都可以加）

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {

                                    //创建一个线程专门用来跑这些界面（登录界面，主界面）的界面层
                                    new Thread(()->{new CLoginViewHandler(ctx);},"system.in").start();
                                }

                            });
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