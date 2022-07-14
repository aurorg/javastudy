import java.io.*;

public class CreateLargeFile {
    public static void main(String[] args) throws Exception{
        DataOutputStream output =
                new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/home/shizhanli/1234.txt")));
        for(int i=0;i<2000000;i++){
           output.writeInt((int)(Math.random()*100000));
        }
        output.close();

        DataInputStream input =
                new DataInputStream(new BufferedInputStream(new FileInputStream("/home/shizhanli/1234.txt")));

        for(int i=0;i<100;i++){
            System.out.print(input.readInt() + " ");
        }
        input.close();

    }
}
