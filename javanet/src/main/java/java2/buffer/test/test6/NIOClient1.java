package java2.buffer.test.test6;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NIOClient1 {
    public static void main(String[] args) {
        //1.打开通道
        SocketChannel socketChannel =SocketChannel.open();

        //2.设置连接IP和端口号
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));

        //3.写数据
        socketChannel.write(ByteBuffer.wrap("老板，该还钱啦".getBytes(StandardCharsets.UTF_8)));
        
    }
}
