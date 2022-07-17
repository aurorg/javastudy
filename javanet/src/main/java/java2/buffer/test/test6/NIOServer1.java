package java2.buffer.test.test6;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NIOServer1 {
    public static void main(String[] args) throws IOException,InterruptedException {
        //打开一个服务端通道
        ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();

        //2.绑定对应的端口号
        serverSocketChannel.bind(new InetSocketAddress((9999)));

        //3.通道默认是阻塞的，需要设置为非阻塞
        //true:通道阻塞，false:通道非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务器启动成功.....");
        while(true){
            //4.检查用户是否有客户端连接，有客户端连接会返回对应的通道，否则的话返回null
            SocketChannel socketChannel =serverSocketChannel.accept();

            if(socketChannel==null){
                System.out.println("没有客户端连接....我去做别的事情啦");
                Thread.sleep(6000);
                continue;
            }

            //5获取用户端传递过来的数据，并且把数据放在bytebuffer这个缓冲区里面
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            int read =socketChannel.read(byteBuffer);
            System.out.println("客户端消息：" +
                    new String(byteBuffer.array(),0,read, StandardCharsets.UTF_8));

            //6.给客户端回写数据
            socketChannel.write(ByteBuffer.wrap("没钱".getBytes(StandardCharsets.UTF_8)));

            //7.释放资源
            socketChannel.close();
        }
    }
}
