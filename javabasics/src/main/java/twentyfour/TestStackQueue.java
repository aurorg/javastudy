package twentyfour;

public class TestStackQueue {
    public static void main(String[] args){
        GenericStack<String> stack = new GenericStack<>();
    }
}

interface SzlQueue<E>{
     
}
class GenericStack<E> implements SzlQueue{
    private java.util.LinkedList<E> list = new java.util.LinkedList<>();


}

