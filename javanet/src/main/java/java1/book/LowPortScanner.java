package java1.book;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class LowPortScanner {
    public static void main(String[] args) {
        String host = args.length >0 ? args[0] : "localhost";
        for(int i=1;i<1024;i++){
            try{
                Socket s = new Socket(host,i);
                System.out.println("There is a server on port " + i + " of "+host);
                s.close();
            }catch(UnknownHostException ex){
                System.err.println(ex);
            }catch(IOException ex){
                //当这个端口不是一个服务器的时候
            }
        }
    }
}

//输出：There is a server on port 631 of localhost
