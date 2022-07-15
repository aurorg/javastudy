package java2.buffer.test.test2;

import java.nio.ByteBuffer;

public class PutBufferDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println("当前索引的位置 " +
                byteBuffer.position());

        System.out.println("最多能到达哪个索引 " +
                byteBuffer.capacity());

        System.out.println("返回缓冲区的长度 " +
                byteBuffer.capacity());

        System.out.println("还能有多少个操作 " +
                byteBuffer.remaining());

        //添加一个字节
        byteBuffer.put((byte) 97);

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());


    }
}
