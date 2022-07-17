package java2.buffer.test.test6;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NIOClient {
    public static void main(String[] args) throws IOException {

        //1.打开通道
        SocketChannel socketChannel=SocketChannel.open();

        //2.设置连接IP和端口号
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));

        //3.写数据
        socketChannel.write(ByteBuffer.wrap("老板，该还钱了".getBytes(StandardCharsets.UTF_8)));

        //4.读取服务器写回的数据
        ByteBuffer readBuffer =ByteBuffer.allocate(1024);
        int read =socketChannel.read(readBuffer);

        //String(byte[] bytes, int offset, int length, Charset charset)
        //构造一个新的String通过使用指定的指定字节子阵列解码charset 。
        //UTF_8万国码的升级版

        System.out.println("服务器消息：" + new String(readBuffer.array(),0,read,StandardCharsets.UTF_8));

        //5.释放资源
        socketChannel.close();

    }
}
