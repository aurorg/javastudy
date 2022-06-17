import java.util.Scanner;
public class PalindromeIgnoreNonAlphanumeric {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a string : ");
        String s = input.nextLine();

        System.out.println("Ignoring nonalphanumeric characters, \nis "
                           + s + " a palindrome? " + isPalindrome(s));
    }
    public static boolean isPalindrome(String s){
        String s1 = filter(s);

        String s2 = reverse(s1);

        return s2.equals(s1); //用equals方法对倒置后的字符串和去掉多余杂质的字符串进行比较
    }
    //逐个检测字符串s中的每个字符，如果字符是字母或者数字字符，则将其复制到字符串构建器
    public static String filter(String s) {
        StringBuilder stringBuilder = new StringBuilder(); //创建一个空的字符串

        for (int i = 0; i < s.length(); i++) {
            //用Character.isLetterOrDigit(s)方法来检测字符s是否是字母或者数字
            if (Character.isLetterOrDigit(s.charAt(i))) {
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();  //filter方法返回构建器中的字符串
    }

    //创建一个新的字符串，这个字符串是过滤后字符串的倒置
    public static String reverse(String s){
        StringBuilder stringBuilder = new StringBuilder(s);
        stringBuilder.reverse();
        return stringBuilder.toString();//返回倒置后的字符串
    }
}
