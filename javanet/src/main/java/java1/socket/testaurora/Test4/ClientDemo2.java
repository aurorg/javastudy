package java1.socket.testaurora.Test4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo2 {
    public static void main(String[] args) {
        while(true){
            Socket s = null;
            try{
                s = new Socket("127.0.0.1",9999);

                OutputStream os =s.getOutputStream();
                System.out.println("zouge说：");
                Scanner sc = new  Scanner(System.in);
                String msg =sc.nextLine();
                os.write(msg.getBytes());

                InputStream is =s.getInputStream();
                byte[] b =new byte[1024];
                int read =is.read(b);
                System.out.println("aurora说："
                            + new String(b,0,read).trim());
            }catch(IOException e){
                e.printStackTrace();
            }finally{
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
