public class WriteData {
    public static void main(String[] args)  throws java.io.IOException{  //因为使用PrintWriter的构造方法可能会抛出一个I/O异常
        java.io.File file = new java.io.File("/home/shizhanli/luck.txt");
        //检测文件是否存在
        if(file.exists()){
            System.out.println("File already exists");
            System.exit(1);
        }
        //如果文件不存在呢，那就调用PrintWriter的构造方法创建一个新文件
        java.io.PrintWriter output = new java.io.PrintWriter(file);

        output.print("Jone T Smith ");
        output.println(90);
        output.print("Eric K Jones");
        output.println(85);

        output.close();//关闭文件
    }
}
