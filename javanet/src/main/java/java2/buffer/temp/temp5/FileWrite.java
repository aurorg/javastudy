package java2.buffer.temp.temp5;
//向本地文件写数据
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileWrite {
    public static void main(String[] args)  throws IOException{
        FileOutputStream out = new FileOutputStream("/home/shizhanli/szl/happy.txt");
        FileChannel channel = out.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("aurora123456789".getBytes());

// 需要切换为读模式，因为下面调用write方法时相当于从buffer里面读数据
        buffer.flip();

// 向channel写数据
        int len = channel.write(buffer);
        System.out.println("字节数："+len);

        out.close();


    }
}
