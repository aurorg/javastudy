package temp.test;

import java.util.Scanner;

public class Test1 {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int userid =8;
        System.out.println("[输入Q返回主界面(F发文件Y收文件）]：");
        String chatmessage;
        chatmessage=input.next() + "  用户："+userid + "说的";
        System.out.println(chatmessage);
    }
}
