package TEST.Test2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestTCPclient {
    public static void main(String[] args) {
        //初始化
        Socket socket =null;
        OutputStream os = null;

        //创建对象
        try {
            InetAddress inet = InetAddress.getByName("127.0.0.1");

            socket = new Socket(inet,2022);

            os = socket.getOutputStream();

            os.write("你好，我是客户端小杨同学\n".getBytes());
            os.write("szl,你在干嘛".getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(socket!=null){
                try {
                     socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
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
}
