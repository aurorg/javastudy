package java2.buffer.temp.temp1;

import java.nio.ByteBuffer;

//创建缓冲区
public class CreateBufferDemo {
    public static void main(String[] args) {

        //1.创建一个指定长度的缓冲区，以ByteBuffer为例
        //什么内容都没有存，会初始化为0
        ByteBuffer byteBuffer =ByteBuffer.allocate(5);
        for(int i=0;i<5;i++){
            System.out.println(byteBuffer.get());
        }

        //在这里调用会报错——等到后面再读缓冲区的时候我们来了解
        //System.out.println(byteBuffer.get());

        //2.创建一个有内容的缓冲区
        ByteBuffer wrap =ByteBuffer.wrap("lagou".getBytes());
        for (int i = 0; i <5; i++) {
            System.out.println(wrap.get());
        }
    }
}
//输出：
//        0
//        0
//        0
//        0
//        0
//        108
//        97
//        103
//        111
//        117
