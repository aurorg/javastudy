package temp1111;

class temp1 {
    public static void main(String[] args) {
        myThread t1 = new myThread();
        t1.start();

        myThread t2 = new myThread();
        t2.start();

        for(int i=0;i<10;i++){
            System.out.println("*******main*******");
        }
    }
}

class myThread extends Thread{
    @Override

    public void run() {
         for(int i=0;i<100;i++){
             if(i%2==0){
                 System.out.println(i);
             }
         }
    }
}
