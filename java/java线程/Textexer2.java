//创建两个分线程，一个遍历偶数，一个遍历奇数

  class Testexer2 {
    public static void main(String[] args) {

        //简便方法：
        //创建Thread类的匿名子类的方式
        new Thread(){
            @Override
            public void run(){
                for(int i=0;i<100;i++){
                    if(i % 2 != 0){
                        System.out.println(Thread.currentThread().getName() + ":" +i);
                    }
                }
            }
        }.start();

        //创建Thread类的匿名子类的方式的另一种表达

//        new Thread(() -> {
//            for (int i = 0; i < 100; i++) {
//                if (i % 2 == 0) {
//                    System.out.println(Thread.currentThread().getName() + ":" + i);
//                }
//            }
//        }).start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 != 0) {
                          System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();


    }
}


