package BOOK;

//查找本地机器的地址

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException e) {
            System.out.println("Could not find this computer's address!");
        }
    }
}

//输出是：
//shizhanli/192.168.30.151
