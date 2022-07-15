package java2.buffer.temp.temp4;

//FileChannel示例

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/home/shizhanli/szl/happy.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }


            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
        System.out.println("读完啦");
    }
}
//输出：
//Read 12
//12345678910（这是我文件中的内容）
//读完啦