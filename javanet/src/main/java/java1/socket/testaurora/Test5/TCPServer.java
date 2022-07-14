package java1.socket.testaurora.Test5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is =null;
        ByteArrayOutputStream baos = null;
        OutputStream os = null;

        try{
            serverSocket = new ServerSocket(8888);

            socket =serverSocket.accept();

            is =socket.getInputStream();

            baos = new ByteArrayOutputStream();
            int len =0;
            byte[] buffer = new byte[1024];
            while((len=is.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            System.out.println("收到了来自客户端 " + socket.getInetAddress()
                             + " 的消息： " + baos.toString());

            os =socket.getOutputStream();
            os.write("你好，我是服务端".getBytes());

        }catch(IOException e){
            e.printStackTrace();
        }finally{
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
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
