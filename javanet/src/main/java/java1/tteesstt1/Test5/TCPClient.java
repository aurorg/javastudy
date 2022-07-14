package java1.tteesstt1.Test5;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args){
        Socket socket = null;
        OutputStream os = null;
        ByteArrayOutputStream baos = null;
        InputStream is =null;

        try{
            InetAddress inet =InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet,8888);

            os = socket.getOutputStream();

            os.write("你好，我是客户端！ ".getBytes());

            socket.shutdownOutput();

            is =socket.getInputStream();
            baos = new ByteArrayOutputStream();
            int len =0;
            byte[] buffer = new byte[1024];
            while((len=is.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            System.out.println("收到了来自服务端的消息：" + baos.toString());
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(socket!=null){
                try {
                    socket.close();
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
            if(is!=null){
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
