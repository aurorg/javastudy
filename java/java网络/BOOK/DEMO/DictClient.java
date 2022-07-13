package BOOK.DEMO;

import java.io.*;
import java.net.Socket;

public class DictClient {
    public static final String SERVER ="dict.org";
    public static final int PORT =2628;
    public static int TIMEOUT =15000;
    public static void main(String[] args) {
        Socket socket =null;
            try {
                //先向一个dict服务器的端口2628打开一个Socket
                socket = new Socket(SERVER,PORT);

                //为了防止连接服务器是服务器挂起，设置一个超时时间
                socket.setSoTimeout(TIMEOUT);


                /**
                 * getOutputStream()方法返回一个原始的OutputStream
                 * 可以用它从你的应用向Socket的另一端写数据。
                 * 在使用之前，通常会把这个流串链到一个更方便的类【DataOututStream或OutputStreamWriter】
                 */
                //getOutputStream()方法返回一个原始的OutputStream
                OutputStream out = socket.getOutputStream();

                //包装在一个Writer中
                Writer writer =new OutputStreamWriter(out,"UTF-8");
                writer =new BufferedWriter(writer);

                //用socket的输入流来读取信息
                InputStream in =socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                for(String word :args){
                    define(word,writer,reader);
                }

                //通过socket写入命令
                writer.write("quit\r\n");

                //刷新输出，确保命令会通过网络发送
                writer.flush();

            } catch (IOException ex) {
                System.out.println(ex);
            }finally{
                if(socket!=null){
                    try {
                        socket.close();
                    } catch (IOException ex) {

                    }
                }
            }
    }

    static void define(String word,Writer writer,BufferedReader reader)
        throws IOException,UnsupportedEncodingException{
        writer.write("DEFINE eng-lat " + word + "\r\n");
        writer.flush();

        for(String line =reader.readLine();line!=null;line=reader.readLine()){
            if(line.startsWith("250 ")){
                return ;
            }else if(line.startsWith("552 ")){
                System.out.println("No definition found for " + word);
                return;
            }
            else if(line.matches("\\d\\d\\d .*"))
                continue;
            else if(line.trim().equals("."))
                continue;
            else System.out.println(line);
        }
    }
}
