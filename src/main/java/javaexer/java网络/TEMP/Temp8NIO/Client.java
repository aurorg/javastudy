package TEMP.Temp8NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.bind(new InetSocketAddress("127.0.0.1", 8088));
        client.connect(new InetSocketAddress("127.0.0.1", 9999));

        ByteBuffer buffer = ByteBuffer.allocateDirect(2826);
        buffer.put("Hello World.".getBytes());
        buffer.flip();

        client.write(buffer);

        client.close();
    }
}


