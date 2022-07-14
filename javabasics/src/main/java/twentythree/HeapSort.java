package twentythree;

import java.util.Comparator;

public class HeapSort {
    public static <E> void heapSort(E[] list){
        heapSort(list,(e1,e2) -> ((Comparable<E>)e1).compareTo(e2));
    }

    public static <E> void heapSort(E[] list,Comparator<E> c){
        Heap<E> heap = new Heap<>(c);

        for(int i =0;i<list.length;i++){
            heap.add(list[i]);
        }

        for(int i =list.length -1;i>=0;i--)
            list[i] = heap.remove();
    }

    public static void main(String[] args){
        Integer[] list = {22,-2,8,6,-5,3,99,-100};
        heapSort(list);
        for(int i =0;i<list.length;i++){
            System.out.print(list[i] + " ");
        }
    }

}
//创建一个类
class Heap<E>{
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();//堆的内部使用线性表实现的
    private java.util.Comparator<? super E> c;

    public Heap(){//无参构造方法创建一个空堆
        this.c =(e1,e2) -> ((Comparable<E>)e1).compareTo(e2);//用lambda表达式创建一个比较器，使用元素的自然顺序作为比较器
    }

    public Heap(java.util.Comparator<E> c){//构造器
        this.c =c;
    }


    public Heap(E[] objects){
        this.c = (e1,e2) ->((Comparable<E>)e1).compareTo(e2);
        for(int i=0;i<objects.length;i++)
            add(objects[i]);
    }

    //******************************************************************************************************************
    //将一个对象添加到树中，如果该对象大于它的父结点，就交换它们；直到该新对象成为根结点，或者该新对象不大于它的父结点
    public void add(E newObject){
        list.add(newObject);
        int currentIndex = list.size() -1;

        while(currentIndex >0){
            int parentIndex =(currentIndex -1)/2;
            if(c.compare(list.get(currentIndex),list.get(parentIndex))>0){
                E temp = list.get(currentIndex);
                list.set(currentIndex,list.get(parentIndex));
                list.set(parentIndex,temp);
            }
            else{
                break;
            }
            currentIndex = parentIndex;
        }

    }
    //*********************************************************************************************************************


    //******************************************************************************************************************
    //删除并且返回根结点。为了保持堆的特征，该方法将最后的对象移动到根结点处；
    // 如果该对象小于它的较大子结点，就互换；直到最后一个对象称为叶子结点，或者呢，该对象不小于它的子结点
    public E remove(){
        if(list.size() == 0)
            return null;
        E removeObject = list.get(0);
        list.set(0,list.get(list.size() -1));

        list.remove(list.size() -1);

        int currentIndex =0;
        while(currentIndex < list.size()){
            int leftChildIndex = 2*currentIndex +1;
            int rightChildIndex = 2*currentIndex +2;

            if(leftChildIndex >= list.size())
                break;
            int maxIndex = leftChildIndex;
            if(rightChildIndex <list.size()){
                if(c.compare(list.get(maxIndex),list.get(rightChildIndex))<0){
                    maxIndex = rightChildIndex;
                }
            }

            if(c.compare(list.get(currentIndex),list.get(maxIndex))<0){
                E temp = list.get(maxIndex);
                list.set(maxIndex,list.get(currentIndex));
                list.set(currentIndex,temp);
                currentIndex = maxIndex;
            }
            else{
                break;
            }
        }
        return removeObject;
    }

    public int getSize(){//返回堆的大小
        return list.size();
    }

    public boolean isEmpty(){//判断该堆是不是空的
        return list.size() ==0;
    }
}
