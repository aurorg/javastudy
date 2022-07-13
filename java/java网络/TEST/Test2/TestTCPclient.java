package TEST.Test2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TestTCPclient {
    public static void main(String[] args) {
        Socket socket =null;
        OutputStream os =null;
        try{
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket =new Socket(inet,2022);

            os =socket.getOutputStream();

            os.write("你好，我是客户端哦".getBytes());
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
        }
    }
}

