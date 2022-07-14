package TEMP.Temp8;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.bind(new InetSocketAddress("127.0.0.1", 8527));
        client.connect(new InetSocketAddress("127.0.0.1", 9090));

        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
        buffer.put("Hello World.".getBytes());
        buffer.flip();

        client.write(buffer);

        client.close();
    }
}


