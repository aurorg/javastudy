public class Test3 {
    public static void main(String[] args){
        AAAA a = new AAAA(3);
    }
}

class AAAA extends BBBB{
    public AAAA(int t){
        System.out.println("A's constructor is invoked");
    }
}

class BBBB{
    public BBBB(){
        System.out.println("B's constructor is invoked");
    }
}
