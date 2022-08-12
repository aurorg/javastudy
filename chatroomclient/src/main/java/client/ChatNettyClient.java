package client;

import client.clienthandler.CDengLuViewHandler;
import common.MessageCodec;
import common.ProtocolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import message.HeartbeatMessage;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ChatNettyClient {

    public static final Object waitMessage=new Object();//服务端消息返回时，notify线程去通知
    public static final Object waitfile=new Object();

    public static volatile int waitSuccess=0;//1表示消息成功、0表示消息失败

    public static volatile boolean unRead1=false;//默认没有未读好友消息
    public static volatile boolean unRead2=false;//默认没有未读群消息

    public static volatile boolean is1=false; //用来判断好友发消息的
    public static volatile boolean is2=false; //用来判断群发消息的

    public static volatile int talker=0; //聊天的人（发文件用）
    public static volatile String check="n";//no
    public static volatile boolean isyes=false;
    public static volatile String havefile="";


    public static volatile Map<String, List<String>> informationMap;//

    public static volatile List<String> friendmsglist;//查询好友历史消息列表
    public static volatile List<String> friendmsglist1;//查询好友未读消息列表
    public static volatile List<String> friendlist; //查询好友列表的
    public static volatile File FriendGetFilemsg; //好友保存文件到本地的
    public static volatile File GroupGetFilemsg;  //群聊保存文件到本地的

    public static volatile List<String> grouplist; //查询好友列表的
    public static volatile List<String> grouphistorymsg; //查询群历史消息
    public static volatile List<String> groupunreadmsg;//查询群未读消息的

    public static volatile int chatting=0;

    public static volatile int fileLength; //发送文件的总的大小
    public static Properties properties=new Properties();
    public static volatile BlockingQueue<Object> blockingQueue=new ArrayBlockingQueue<>(5);
    public static String path;
    public static volatile RandomAccessFile breakPointSend;//处理发送断点续传配置文件
    public static String breakPointSendPath=ChatNettyClient.class.getClassLoader().getResource("breakPointSend").getPath();
    public static volatile RandomAccessFile breakPointReceive;//处理接收断点续传配置文件
    public static String breakPointReceivePath=ChatNettyClient.class.getClassLoader().getResource("breakPointReceive").getPath();


    public static void main(String[] args) throws Exception{

        //客户端需要一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();

        LoggingHandler Log=new LoggingHandler(LogLevel.DEBUG);
      //  MessageCodec clientCodec=new MessageCodec();
        properties.load(new java.io.FileInputStream(ChatNettyClient.class.getClassLoader().getResource("application.properties").getPath()));


        try {
            //创建客户端启动对象
            //注意客户端使用的不是ServerBootstrap,而是Bootstrap
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) //设置客户端通道的实现类
                    .handler( new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProtocolFrameDecoder());
                            ch.pipeline().addLast(new MessageCodec()); //解码编码的


                            //用来判断是不是读空闲时间过长，或者写空闲时间过长
                            //8s内如果没有收到channel的数据，就会触发IdleStateREADER_IDLE事件
                            //IdleStateHandler是netty提供的处理空闲状态的处理器，有三个参数
                            //int readerIdleTimeSeconds :表示多长时间没有读，就会发送一个心跳检测包检测是否还处于连接状态
                            // int writerIdleTimeSeconds ：表示多长时间没有写，就会发送一个心跳检测包检测是否还处于连接状态
                            // int allIdleTimeSeconds：表示多长时间既没有读又没有写，就会发送一个心跳检测包检测是否还处于连接状态

                            ch.pipeline().addLast(new IdleStateHandler(0, 8, 0));
                            ch.pipeline() .addLast(new ChannelDuplexHandler() {
                                @Override
                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                    IdleStateEvent event = (IdleStateEvent) evt;
                                    if (event.state() == IdleState.WRITER_IDLE) {
                                        ctx.writeAndFlush(new HeartbeatMessage());
                                    }
                                    super.userEventTriggered(ctx, evt);
                                }
                            });

                            ch.pipeline().addLast(new ResponseHandler()); //服务端给客户端回消息的处理器
                            ch.pipeline().addLast(new CFriendChatHandler());//好友聊天的
                            ch.pipeline().addLast(new CGroupChatHandler()); //群聊的
                            //ch.pipeline().addLast(new ServerToClientmsg());
                            ch.pipeline().addLast("CLoginViewHandler",new ChannelInboundHandlerAdapter(){  //加入自己的处理器,需要什么处理器加什么处理器（这个后面都可以加）

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {

                                    //创建一个线程专门用来跑这些界面（登录界面，主界面）的界面层
                                    new Thread(()->{new CDengLuViewHandler(ctx);},"system.in").start();
                                }

                            });
                        }
                    });

            System.out.println("*******************************客户端ok*********************************");
            System.out.println("*******************************客户端ok*********************************");

            //启动客户端取连接服务端
            //关于ChannelFuture需要分析：涉及netty的异步模型
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6686).sync();

            //给关闭通道增加一个连接进行监听
            channelFuture.channel().closeFuture().sync();
        }finally{
            group.shutdownGracefully();
        }
    }
    public static void wait1(){
        try {
            synchronized (waitMessage) {
                waitMessage.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}