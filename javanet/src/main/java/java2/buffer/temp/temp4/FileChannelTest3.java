package java2.buffer.temp.temp4;

//transferTo()将数据从FileChannel传输到其他的Channel中。
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelTest3 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/home/shizhanli/szl/happy.txt", "rw");
        FileChannel fromChannel = aFile.getChannel();

        RandomAccessFile bFile = new RandomAccessFile("/home/shizhanli/szl/happy333.txt", "rw");
        FileChannel toChannel = bFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        fromChannel.transferTo(position, count, toChannel);
        aFile.close();
        bFile.close();
        System.out.println("over!");
    }

}