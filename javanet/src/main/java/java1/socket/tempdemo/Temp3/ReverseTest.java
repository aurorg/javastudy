package java1.socket.tempdemo.Temp3;

//给定地址，找出主机名
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReverseTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia = InetAddress.getByName("192.168.30.151");
        System.out.println(ia.getCanonicalHostName());

    }
}

//输出结果：
//shizhanli.lan