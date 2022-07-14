package TEST.Test9;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        Socket socket = null;
        FileInputStream fis =null;
        OutputStream os =null;
        try{
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet,8888);
            fis = new FileInputStream("/home/shizhanli/szl/happy.txt");

            os =socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int len =0;
            while((len=fis.read(buffer))!=-1){
                os.write(buffer,0,len);
            }
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
            if(fis!=null){
                try {
                    fis.close();
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
