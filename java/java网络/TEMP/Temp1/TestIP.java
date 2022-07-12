package TEMP.Temp1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIP {
    public static void main(String[] args) throws UnknownHostException {
        //InetAdress类表示IP地址

        //获取本机IP
        InetAddress ip = InetAddress.getLocalHost();//  shizhanli/192.168.30.151
        System.out.println(ip);

        //获得主机名
        System.out.println(ip.getHostName());// shizhanli

        //获得IP地址
        System.out.println(ip.getHostAddress());// 192.168.30.151

        //getLocalHost=getHostName+getHostAddress
        System.out.println(InetAddress.getByName("localhost"));//localhost/127.0.0.1
    }
}

