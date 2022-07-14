package java1.tteemmpp1.Temp3;

//找到本地机器的IP地址

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {
    public static void main(String[] args) {
        try{
            InetAddress me = InetAddress.getLocalHost();
            String dottedQuad = me.getHostAddress();//创建一个String类型的变量来接受返回的ip地址
            System.out.println("My address is " + dottedQuad);
        }catch (UnknownHostException ex){
            System.out.println("I'm sorry.I don't know my own address.");

        }
    }
}

//输出：My address is 192.168.30.151
