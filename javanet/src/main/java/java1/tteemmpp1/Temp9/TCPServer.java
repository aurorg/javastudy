package java1.tteemmpp1.Temp9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            //1、创建服务端的ServerSocket，指明自己的端口号
            serverSocket = new ServerSocket(8888);
            //2、调用accept接收到来自于客户端的socket
            socket = serverSocket.accept();//阻塞式监听，会一直等待客户端的接入
            //3、创建一个文件输出流，用于将读取到的客户端上传的文件输出
            fos = new FileOutputStream("/home/shizhanli/szl/happy111.txt");
            //4、获取socket的输入流
            is = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int len=0;
            while((len=is.read(buffer))!=-1){
                fos.write(buffer,0,len);//5、写出文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//6、释放资源
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
