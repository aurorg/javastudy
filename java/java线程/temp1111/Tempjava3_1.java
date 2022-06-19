package temp1111;

public class Tempjava3_1 {
    public static void main(String[] args) {
        Number number = new Number();

        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程一");
        t2.setName("线程二");

        
        t1.start();
        t2.start();
    }
}

class Number implements Runnable{
    private int number =1;

    @Override
    public void run() {
        while(true){
        synchronized(this){
            this.notify();
            if(number <=100){

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()
                + ":" +number);
                number++;

                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
                 }

            }
        }
    }
}
