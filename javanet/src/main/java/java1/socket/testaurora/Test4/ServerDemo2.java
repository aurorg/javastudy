package java1.socket.testaurora.Test4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDemo2 {
    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器已经启动");
        while(true){
            Socket socket =serverSocket.accept();
            System.out.println("有客户端连接");

            executorService.execute(new Runnable(){
                @Override
                public void run() {
                    handle(socket);
                }
            });

            }
        }
        public static void handle(Socket socket){
        try{
            System.out.println("线程ID： " + Thread.currentThread().getId()
                     + "    线程名称：" + Thread.currentThread().getName());
            InputStream is =socket.getInputStream();
            byte[] b = new byte[1024];
            int read = is.read(b);
            System.out.println("客户端： " +new String(b,0,read));

            OutputStream os =socket.getOutputStream();
            Scanner sc1 = new Scanner(System.in);
            String msg1 =sc1.nextLine();

            os.write(msg1.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
