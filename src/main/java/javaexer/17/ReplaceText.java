import java.io.*;
import java.util.*;
public class ReplaceText {
    public static void main(String[] args) throws Exception{
        if(args.length !=4){
            System.out.println("Usage: java ReplaceText sourceFile targetFile oldStr newStr");
            System.exit(1);
        }

        File sourceFile = new File(args[0]);
        if(!sourceFile.exists()){
            System.out.println("Source file " + args[0] + "does not exist");
            System.exit(2);
        }

        File targetFile = new File(args[1]);
        if(targetFile.exists()){
            System.out.println("Target file " + args[1] + "already exists");
            System.exit(3);
        }

        try(
            Scanner input = new Scanner(sourceFile); //为源文件创建一个Scanner
            PrintWriter output = new PrintWriter(targetFile);//为目标文件创建一个PrintWriter
        ){
            while (input.hasNext()){
                String s1 = input.nextLine();
                String s2 =s1.replaceAll(args[2],args[3]);//替换
                output.println(s2);
            }
        }

    }
}
