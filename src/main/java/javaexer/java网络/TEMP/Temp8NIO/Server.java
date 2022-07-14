package TEMP.Temp8NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) throws IOException {
        List<SocketChannel> clients = new ArrayList<>();

        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress("127.0.0.1", 9999));
        server.configureBlocking(false);

        while (true) {
            SocketChannel socketChannel = server.accept();

            if (socketChannel != null) {
                socketChannel.configureBlocking(false);
                clients.add(socketChannel);
                System.out.println("Client connected: " + socketChannel.socket().getPort());
            }

            ByteBuffer buffer = ByteBuffer.allocateDirect(2826);

            for (SocketChannel channel : clients) {
                int num = channel.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);

                    System.out.println(channel.socket().getPort() + ": " + new String(bytes));
                    buffer.clear();
                }
            }
        }
    }
}
