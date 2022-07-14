package TEMP.Temp7BIO;

//        1创建ServerSocket对象，绑定IP、端口号并开始监听
//        2Server端通过accept()函数阻塞调用
//        3Client端发起连接
//        4在内核层面完成tcp三次握手，并创建Socket四元组(server: ip port/client: ip port)以及相应的数据缓冲区
//        5Server端accept()函数返回新的Socket
//        6开始处理消息

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9090, 20);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService service = new ThreadPoolExecutor(0, 100, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        while (true) {
            try {
                Socket client = serverSocket.accept();
                System.out.println("client connected: " + client.getPort());

                service.execute(() -> {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        while (true) {
                            String line = reader.readLine();
                            if (null != line) {
                                System.out.println(line);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

