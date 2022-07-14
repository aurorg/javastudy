package java1.book;
//显示www.oreilly.com地址的程序
import java.net.InetAddress;
import java.net.UnknownHostException;

public class OReillyByName {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.oreilly.com");
            System.out.println(address);
        } catch (UnknownHostException e) {
            System.out.println("Could not find www.oreilly.com");

        }
    }
}
