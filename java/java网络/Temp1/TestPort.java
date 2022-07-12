package Temp1;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class TestPort {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",2022);

        System.out.println(inetSocketAddress);
        //返回主机名

        System.out.println(inetSocketAddress.getHostName());
        //获得InetSocketAddress的端口

        System.out.println(inetSocketAddress.getPort());
        //返回一个InetAddress对象（IP对象）

        InetAddress address = inetSocketAddress.getAddress();
        System.out.println(address);
    }
}