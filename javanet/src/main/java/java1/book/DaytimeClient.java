package java1.book;
//Daytime协议客户端
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class DaytimeClient {
    public static void main(String[] args) {
        String hostname = args.length>0?args[0] : "time.nist.gov";
        Socket socket = null;
        try{
            /**在端口13打开与time.nist.gov的连接
             * 这里不仅仅是创建一个对象，实际上它会在网络上建立连接；
             * 如果连接超时（或者由于服务器没有在端口13上监听而失败），
             * 构造函数会抛出一个IOException异常
             * 所以呢，要把这段代码放在一个try块中。
             */

            //在端口13打开与time.nist.gov的连接
            socket = new Socket(hostname,13);

            //setSoTimeout（）方法为连接设置一个超时时间，（单位：毫秒）
            socket.setSoTimeout(15000);

            //一旦打开socket并设置其超时时间，
            // 可以调用getInputStream（）来返回一个InputStream,用它从socket读取字节
            InputStream in = socket.getInputStream();

            //把字节存储在一个StringBuilder中
            StringBuilder time =new StringBuilder();

            //一般情况下，服务器可以发送任何字节
            //但是呢，在特定的例子中，协议指定发送的字节必须是ASCII
            InputStreamReader reader = new InputStreamReader(in,"ASCII");

            //使用for确定将字节读完
            for(int c=reader.read();c!=-1;c=reader.read()){
                time.append((char) c);
            }
            System.out.println(time);
        }catch(IOException ex){
            System.err.println(ex);
        }finally{
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

//输出
//59773 22-07-13 02:17:17 50 0 0 720.0 UTC(NIST) *
