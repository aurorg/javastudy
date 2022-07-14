import java.io.*;
public class TestFileStream {
    public static void main(String[] args) throws IOException{
        try(
                FileOutputStream output = new FileOutputStream("/home/shizhanli/luck.txt");//创建一个FileOutputStream对象
                ) {
            for (int i = 1; i <= 10; i++) {
                output.write(i);//将十个值写入到文件中
            }
        }
        try(
                FileInputStream input = new FileInputStream("/home/shizhanli/luck.txt");
                ){
            int value ;
            while ((value = input.read()) !=-1)//从文件中读取字节值并且显示
                System.out.print(value + " ");
        }
    }
}

