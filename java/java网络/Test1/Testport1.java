package Test1;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Testport1 {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",2022);

        System.out.println(inetSocketAddress);
        System.out.println(inetSocketAddress.getHostName());
        System.out.println(inetSocketAddress.getPort());
        InetAddress address = inetSocketAddress.getAddress();
        System.out.println(address);
    }
}
