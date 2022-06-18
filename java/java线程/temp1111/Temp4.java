package temp1111;

public class Temp4 {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());//使用Thread来驱动LiftOff对象
        t.start();
        System.out.println("waiting for java0.LiftOff");
    }


}

class LiftOff implements Runnable{
    protected int countDown =10;
    private static int taskCount =0;
    private final int id = taskCount++;

    public LiftOff(){

    }

    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" +id + "(" +
                (countDown >0 ? countDown : "Liftoff") +"),";
    }

    @Override
    public void run() {
        while(countDown-->0){
            System.out.println(status());
            Thread.yield();
        }
    }
}
