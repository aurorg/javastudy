class Tests1 {
    public static void main(String[] args){

        new Thread(){
            @Override
            public void run(){
                for(int i=0;i<100;i++){
                    if(i%2==0){
                        System.out.println(Thread.currentThread().getName()+":"+i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run(){
                for(int i=0;i<100;i++ ){
                    if(i%2!=0){
                        System.out.println(Thread.currentThread().getName()+ ":" +i);
                    }
                }
            }
        }.start();
    }
 }
