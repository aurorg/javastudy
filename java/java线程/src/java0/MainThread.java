package src.java0;

class LiftOff1 implements Runnable{
    protected int countDown =10;
    private static int taskCount =0;
    private final int id = taskCount++;
    public LiftOff1(){

    }

    public LiftOff1(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "(" +
                (countDown >0 ? countDown : "Liftoff!")+ ")," ;
    }

    @Override
    public void run(){
        while(countDown-->0){
            System.out.print(status());
            Thread.yield();
        }
    }
}

public class MainThread {
    public static void main(String[] args) {
        //方式一
//        java0.LiftOff launch = new java0.LiftOff();
//        launch.run();

        //方式二
        Thread t = new Thread(new LiftOff1());//使用Thread来驱动LiftOff对象
        t.start();
        System.out.println("waiting for java0.LiftOff");
    }
}

