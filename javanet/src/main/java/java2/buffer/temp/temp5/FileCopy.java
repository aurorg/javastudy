package java2.buffer.temp.temp5;

//文件拷贝

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("/home/shizhanli/szl/happy.txt");
        FileOutputStream out = new FileOutputStream("/home/shizhanli/szl/happycopy.txt");
        FileChannel src = in.getChannel();
        FileChannel dst = out.getChannel();

        src.transferTo(0,src.size(),dst);
        // 或dst.transferFrom(src,0,src.size());

        in.close();
        out.close();

    }
}
