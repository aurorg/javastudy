package java1.socket.testaurora.Test9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket =null;
        FileOutputStream fos =null;
        InputStream is =null;
        try{
            serverSocket = new ServerSocket(8888);
            socket = serverSocket.accept();
            fos =new FileOutputStream("/home/shizhanli/szl/happy111.txt");
            is =socket.getInputStream();
            byte[] buffer = new byte[1024];
            int len=0;
            while((len=is.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }
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
