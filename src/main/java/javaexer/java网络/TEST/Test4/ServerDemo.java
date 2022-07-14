package TEST.Test4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static TEMP.Temp4.ServerDemo.handle;

public class ServerDemo {
    public static void main(String[] args) throws Exception {
        //1.创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //创建socket对象
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器已经启动");
        while (true) {
            //3.监听客户端
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接");

            //4.开启新的线程
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handle(socket);
                }
            });

        }
    }
    public static void handle(Socket socket){
        try{
            System.out.println("线程ID：" + Thread.currentThread().getId()
                    + "    线程名称：" + Thread.currentThread().getName());

            //从连接中取出输入流来接受消息
            InputStream is =socket.getInputStream();
            byte[] b = new byte[1024];
            int read = is.read(b);
            System.out.println("客户端： " + new String(b,0,read));

            //连接中取出输出流并且回话
            OutputStream os = socket.getOutputStream();

            Scanner sc1 = new Scanner(System.in);
            String msg1 =sc1.nextLine();

            os.write(msg1.getBytes());

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                //关闭连接
                socket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}