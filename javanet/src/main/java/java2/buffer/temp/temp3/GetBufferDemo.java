package java2.buffer.temp.temp3;

import java.nio.ByteBuffer;

public class GetBufferDemo {
    public static void main(String[] args) {
        //1.创建一个指定长度的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(10);
        allocate.put("0123".getBytes());
        System.out.println("position:" + allocate.position());
        System.out.println("limit:" + allocate.limit());
        System.out.println("capacity:" + allocate.capacity());
        System.out.println("remaining:" + allocate.remaining());

        //切换读模式
        System.out.println("读取数据--------");
        allocate.flip();
        System.out.println("position:" + allocate.position());
        System.out.println("limit:" + allocate.limit());
        System.out.println("capacity:" + allocate.capacity());
        System.out.println("remaining:" + allocate.remaining());
        for(int i=0;i<allocate.limit();i++){
            System.out.println(allocate.get());//读的是ASCII码值
        }
        //读取完了之后，继续读取就会把错，超过limit值
        //System.out.println(allocate.get());

        //读取指定索引字节
        System.out.println("读取指定索引字节----------");
        System.out.println(allocate.get(1));

        System.out.println("读取多个字节--------");

        //重复读取
        allocate.rewind();//rewind将position设置为0，可以重复读
        byte[] bytes = new byte[4];
        allocate.get(bytes);
        System.out.println(new String(bytes));

        //将缓冲区转化字节数组返回
        System.out.println("将缓冲区转化字节数组返回---------");
        byte[] array =allocate.array();
        System.out.println(new String(array));

        //切换写模式，覆盖之前索引所在位置的值
        System.out.println("写模式--------");
        allocate.clear();
        allocate.put("abc".getBytes());
        System.out.println(new String(allocate.array()));

    }
}
//输出
//        position:4
//        limit:10
//        capacity:10
//        remaining:6
//        读取数据--------
//        position:0
//        limit:4
//        capacity:10
//        remaining:4
//        48（0的ASCII）
//        49
//        50
//        51
//        读取指定索引字节----------
//        49
//        读取多个字节--------
//        0123
//        将缓冲区转化字节数组返回---------
//        0123
//        写模式--------
//        abc3

