package java2.buffer.temp.temp5;

//从本地文件读数据

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileRead {
    public static void main(String[] args)  throws IOException {
        FileInputStream in = new FileInputStream("/home/shizhanli/szl/happy.txt");
        FileChannel channel = in.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(8);

        int len = -1;
        List<Byte> list = new ArrayList<>();
        byte[] bytes = new byte[8];

        // 循环读取数据
        while ((len=channel.read(buffer))!=-1){

            // 下面要从buffer中读数据，因此切换为读模式
            buffer.flip();

            buffer.get(bytes,0,len);
            for (int i = 0; i < len; i++) {
                list.add(bytes[i]);
            }

            // 下一个循环需要先向buffer写数据，因此切换为写模式
            buffer.clear();
        }
        in.close();

      // 转为byte数组
        byte[] resBytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resBytes[i] = list.get(i);
        }

        // 以字符串形式打印
        System.out.println(new String(resBytes));

    }
}
