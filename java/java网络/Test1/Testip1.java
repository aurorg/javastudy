package Test1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Testip1 {
    public static void main(String[] args) {
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(ip);

        System.out.println(ip.getHostName());

        System.out.println(ip.getHostAddress());
        try {
            System.out.println(InetAddress.getByName("localHost"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
