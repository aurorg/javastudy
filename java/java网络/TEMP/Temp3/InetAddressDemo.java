package TEMP.Temp3;

//使用以上方法的小demo

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) {
        try{
            
            //获取自己本机地址信息
            InetAddress localIp=InetAddress.getLocalHost();

            //1获取此IP地址的全限定域名
            System.out.println("1.localIp.getCanonicalHostName()="
                    + localIp.getCanonicalHostName());

            //2获取该InetAddress对象对应的IP地址字符串
            System.out.println("2.localIp.getHostAddress()="
                    + localIp.getHostAddress());

            //3获得该InetAddress对象的主机名称
            System.out.println("3.localIp.getHostName()="
                    + localIp.getHostName());


            System.out.println("4.localIp.toString()="+localIp.toString());

            //5.判断是否可以到达该地址
            System.out.println("5.localIp.isReachable(5000)="
                    + localIp.isReachable(5000));

            System.out.println("====================================");
            System.out.println("====================================");

            //获取指定域名地址的信息([eg]百度）
            InetAddress baiduip = InetAddress.getByName("www.baidu.com");

            //1获取此IP地址的全限定域名
            System.out.println("1.baiduIp.getCanonicalHostName()="
                    + baiduip.getCanonicalHostName());
            //2获取该InetAddress对象对应的IP地址字符串
            System.out.println("2.baiduIp.getHostAddress()="
                    + baiduip.getHostAddress());

            //3获得该InetAddress对象的主机名称
            System.out.println("3.baiduIp.getHostName()="
                    + baiduip.getHostName());

            System.out.println("4.baiduIp.toString()="+baiduip.toString());

            //5判断是否可以到达该地址
            System.out.println("5.baiduIp.isReachable(5000)="
                    + baiduip.isReachable(5000));
            System.out.println("====================================");
            System.out.println("====================================");

            //获取指定原始IP地址信息
            InetAddress ip = InetAddress.getByAddress(new byte[]{127,0,0,1});
            // InetAddress ip = InetAddress.getByName("127.0.0.1");
            System.out.println("1.ip.getCanonicalHostName()=" + ip.getCanonicalHostName());
            System.out.println("2.ip.getHostAddress()= "+ ip.getHostAddress());
            System.out.println("3.ip.getHostName()="+ ip.getHostName());
            System.out.println("4.ip.toString()="+ ip.toString());
            System.out.println("5.ip.isReachable(5000)="
                    + ip.isReachable(5000));
        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

//输出结果
//        1.localIp.getCanonicalHostName()=shizhanli.lan
//        2.localIp.getHostAddress()=192.168.30.151
//        3.localIp.getHostName()=shizhanli
//        4.localIp.toString()=shizhanli/192.168.30.151
//        5.localIp.isReachable(5000)=true
//        ====================================
//        ====================================
//        1.baiduIp.getCanonicalHostName()=14.215.177.38
//        2.baiduIp.getHostAddress()=14.215.177.38
//        3.baiduIp.getHostName()=www.baidu.com
//        4.baiduIp.toString()=www.baidu.com/14.215.177.38
//        5.baiduIp.isReachable(5000)=true
//        ====================================
//        ====================================
//        1.ip.getCanonicalHostName()=localhost
//        2.ip.getHostAddress()= 127.0.0.1
//        3.ip.getHostName()=localhost
//        4.ip.toString()=localhost/127.0.0.1
//        5.ip.isReachable(5000)=true