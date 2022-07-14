package temp1111;

public class Temp3 {
    public static void main(String[] args) {
        HelloThread h1 = new HelloThread("Thread");

        h1.setPriority(Thread.MAX_PRIORITY);

        h1.start();

        Thread.currentThread().setName("主线程");

        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        for(int i=0;i<100;i++){
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+
                        ":" + Thread.currentThread().getPriority() + ":" +i);

            }
        }
    }
}

class HelloThread extends Thread{
    @Override
    public void run() {
         for(int i=0;i<100;i++){
             if(i%2==0){
                 System.out.println(Thread.currentThread().getName()
                 + ":" + Thread.currentThread().getPriority() + ":" +i);

             }
         }
    }

    public HelloThread(String name){
        super(name);
    }
}

