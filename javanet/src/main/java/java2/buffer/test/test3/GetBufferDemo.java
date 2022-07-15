package java2.buffer.test.test3;

import java.nio.ByteBuffer;

public class GetBufferDemo {
    public static void main(String[] args) {
        ByteBuffer allocate =ByteBuffer.allocate(10);
        allocate.put("0123".getBytes());
        System.out.println("position:" + allocate.position());
        System.out.println("limit:" + allocate.limit());
        System.out.println("capacity:" +allocate.capacity());
        System.out.println("remaining:" + allocate.remaining());

        System.out.println("读取数据————----");
        allocate.flip();
        System.out.println("position:"+allocate.position());
        System.out.println("limit:" + allocate.limit());
        System.out.println("capacity: " + allocate.capacity());
        System.out.println("remaining:" + allocate.remaining());
        for(int i=0;i<allocate.limit();i++){
            System.out.println(allocate.get());
        }

        System.out.println("读取指定索引的字节-------");
        System.out.println(allocate.get(1));

        System.out.println("读取多个字节-------");
//        allocate.rewind();
//        byte[] bytes = new byte[4];
//        allocate.get(bytes);
//        System.out.println(new String(bytes));
        allocate.rewind();
        byte[] bytes = new byte[4];
        allocate.get(bytes);
        System.out.println(new String(bytes));

        System.out.println("将缓冲区转化字节数组返回------");
        byte[] array =allocate.array();
        System.out.println(new String(array));

        //切换写模式
        System.out.println("写模式-------");
        allocate.clear();
        allocate.put("abc".getBytes());
        System.out.println(new String(allocate.array()));

    }
}
