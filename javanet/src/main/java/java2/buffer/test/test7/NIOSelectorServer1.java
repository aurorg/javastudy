package java2.buffer.test.test7;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NIOSelectorServer1 {
    public static void main(String[] args) throws IOException,InterruptedException {

        //1.打开一个服务器通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2.绑定对应的端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));

        //3.通道默认是阻塞的，需要设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //4.创建服务器
        Selector selector = Selector.open();

        //5.将服务器通道注册到选择器上面，并且指定注册监听事件OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功.....");
        while(true){

            //6.检查选择器是否有事件
            int select =selector.select(5000);
            if(select == 0){
                continue;
            }

            //7.获取事件集合
            Set<SelectionKey> selectionKeys =selector.selectedKeys();
            Iterator<SelectionKey> iterator =selectionKeys.iterator();

            while(iterator.hasNext()){
                //8.判断事件是否是客户端连接事件SelectionKey.isAcceptable()
                SelectionKey key = iterator.next();

                //9、得到客户端通道，并且将通道注册到选择器上面，并且指定监听事件为OP_READ
                if(key.isAcceptable()){
                    SocketChannel socketChannel =serverSocketChannel.accept();
                    System.out.println("客户端已经连接...." + socketChannel);

                    //必须设置通道为非阻塞，因为selector需要轮询监听每个通道的事件
                    socketChannel.configureBlocking(false);

                    //指定监听事件为OP_READ
                    socketChannel.register(selector,SelectionKey.OP_READ);

                }

                //10.判断是否是客户端读就绪事件SelectionKey.isReadable()
                if(key.isReadable()){
                    //11.得到客户端通道，读取数据到缓存区
                    SocketChannel socketChannel =(SocketChannel)key.channel();
                    ByteBuffer byteBuffer =ByteBuffer.allocate(1024);
                    int read =socketChannel.read(byteBuffer);
                    if(read >0){
                        System.out.println("客户端消息：" +
                                new String(byteBuffer.array(),0,read, StandardCharsets.UTF_8));

                        //12.给客户端回写数据
                        socketChannel.write(ByteBuffer.wrap("没钱".getBytes(StandardCharsets.UTF_8)));
                        socketChannel.close();

                    }
                }

                //13.从集合中删除对应的事件，防止二次处理
                iterator.remove();
            }
        }



    }
}
