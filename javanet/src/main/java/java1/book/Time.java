package java1.book;

//时间协议客户端

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.Date;

public class Time {
    private static final String HOSTNAME = "time.nist.gov";
    public static void main(String[] args) {
        Date d = null;
        try {

            d = Time.getDateFromNetwork();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("It is " + d);
    }

    public static Date getDateFromNetwork() throws IOException,ParseException{
        long differenceBetweenEpochs = 2208988800l;
        Socket socket = null;
        try{
            socket = new Socket(HOSTNAME,37);
            socket.setSoTimeout(15000);
            InputStream raw =socket.getInputStream();

            long secondSince1900 =0;
            for(int i=0;i<4;i++){
                secondSince1900 =(secondSince1900 << 8) | raw.read();
            }
            long secondSince1970 =secondSince1900 - differenceBetweenEpochs;
            long msSince1970 =secondSince1970*1000;
            Date time = new Date(msSince1970);
            return time;
        }finally{
            try{
                if(socket!=null)
                    socket.close();
            }catch(IOException ex){

            }
        }
    }

}

//输出：
// It is Wed Jul 13 11:53:25 CST 2022
