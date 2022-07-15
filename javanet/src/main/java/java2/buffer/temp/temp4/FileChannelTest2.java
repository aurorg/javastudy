package java2.buffer.temp.temp4;

//transferFrom()示例:transferFrom()从其他Channel获取数据
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;


public class FileChannelTest2 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/home/shizhanli/szl/happy.txt", "rw");
        FileChannel fromChannel = aFile.getChannel();

        RandomAccessFile bFile = new RandomAccessFile("/home/shizhanli/szl/happy222.txt", "rw");
        FileChannel toChannel = bFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel, position, count);
        aFile.close();
        bFile.close();
        System.out.println("over!");
    }
}
