package eleven;

import java.util.ArrayList;
import java.util.Scanner;
public class DistinctNumbers {
    public static void main(String[] args){
        //创建了一个存储Integer对象的ArrayList
        ArrayList<Integer> list = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter integers (input ends with 0): ");
        int value;

        do{
            value = input.nextInt();  //使用循环读入数据

            if(!list.contains(value) && value!=0)
                list.add(value);
        }while(value !=0);

        System.out.print("The distinct integers are: ");
        for(int i=0;i<list.size();i++)
            System.out.print(list.get(i)+" ");
        //for循环还可以这样写
        //第一种改法
        /*
        for(Integer number: list)
           System.out.print(number + " ");
         */

        //第二种改法
        /*
        for(int number:list)
           System.out.print(number + " ");
         */
        //list中的元素是Integer对象，它们在for循环中被自动拆箱为int
    }
}
