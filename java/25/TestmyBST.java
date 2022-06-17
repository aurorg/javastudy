import java.util.Collection;

public class TestmyBST {
    public static void main(String[] args){

    }
}


//接口
interface myTree<E>{
    //1.在BST中插入一个元素（新元素插在叶子结点处）
    boolean insert(E e);

    //2.在BST中查找一个元素（二分查找）
    boolean search(E e);

    //3.中序遍历
    void inorder();

    //4.后续遍历
    void postorder();

    //5.前序遍历
    void preorder();

    //6.获取树中的元素
    int getSize();

    //7.返回根结点
    TreeNode<E> getRoot();

    //8.删除一个元素
    boolean delete(E e);

}


//实现方法
class BST<E> implements myTree<E>{
    protected TreeNode<E> root;//根结点
    protected int size =0;
    protected java.util.Comparator<E> c;

    public BST(){
        this.c =(e1,e2) ->((Comparable<E>)e1).compareTo(e2);
    }

    public BST(java.util.Comparator<E> c){
        this.c=c;
    }

    public BST(E[] objects){
        this.c = (e1,e2) -> ((Comparable<E>)e1).compareTo(e2);
        for(int i=0;i<objects.length;i++){
           // add(objects[i]);
        }
    }

    //1.在BST中插入一个元素（新元素插在叶子结点处）
    //分两种情况
    //①这棵树是空的，使用新元素创建一个根结点
    //②这棵树不是空的，1》 寻找新元素结点的父结点的位置；为该元素创建一个新的结点，然后将该结点链接到它的父结点上
    //               2》 如果新元素小于父元素，则将新元素的结点设置为父结点的左子结点
    //               3》 如果新元素大于父元素，则将新元素的结点设置为父结点的右子结点
    @Override
    public boolean insert(E e){
        //如果这棵树是空的，使用新元素创建一个根结点
        if(root==null){
            root = createNewNode(e);//调用创建结点的方法
        }

        //寻找新元素结点的父结点的位置；为该元素创建一个新的结点，然后将该结点链接到它的父结点上
        else{
            TreeNode<E> parent = null;
            TreeNode<E> current = root; //创建一个新的结点指向根结点

            while(current!=null){
                if(c.compare(e,current.element)<0){
                    parent = current;
                    current = current.left;
                }
                else if(c.compare(e,current.element)>0){
                    parent =current;
                    current = current.right;
                }
                else
                    return false;
            }

            //如果新元素小于父元素，则将新元素的结点设置为父结点的左子结点
            if(c.compare(e,parent.element)<0){
                parent.left = createNewNode(e);
            }
            //如果新元素大于父元素，则将新元素的结点设置为父结点的右子结点
            else{
                parent.right = createNewNode(e);
            }
        }
        size++;//大小加1
        return true;
    }
    protected TreeNode<E> createNewNode(E e){
        return new TreeNode<>(e);
    }

    //2.在BST中查找一个元素（二分查找）
    @Override
    public boolean search(E e){
        //创建一个新的结点current，让current指向根结点
        TreeNode<E> current = root;

        //循环，直到current指向null，或者匹配到current.element
        while(current !=null){
            if(c.compare(e,current.element)<0){//e小于current.element这个元素的时候，放在这个元素的左边
                current =current.left;
            }
            else if(c.compare(e,current.element)>0){//e大于current.element这个元素的时候，放在这个元素的右边
                current = current.right;
            }
            else{
                return true;//表示找到了要查找的元素
            }
        }
        return false;//说明这个元素不在这棵树上
    }

    //3.****************中序遍历**************************************
    //每一步都是先将左子节点放入栈中，直到左子节点为空为止，然后再访问，之后再将右子节点放入栈中，如此进行下去。
    //递归的访问当前结点的左子树；然后访问当前结点；最后递归的访问该节点的右子树
    @Override
    public void inorder(){
        inorder(root);
    }

    protected void inorder(TreeNode<E> root){//左->根->右
        if(root ==null)
            return;
        inorder(root.left);//左
        System.out.print(root.element + "");//根
        inorder(root.right);//右
    }


    //4.*******************后序遍历***************************************
    //首先递归的访问当前结点的左子树，然后递归的访问该结点的右子树，最后访问结点本身
    @Override
    public void postorder(){
        postorder(root);
    }

    protected void postorder(TreeNode<E> root){
        if(root == null)
            return;
        postorder(root.left);//左
        postorder(root.right);//右
        System.out.print(root.element + "");//根
    }


    //5.*******************前序遍历*********************************************
    //首先访问当前结点，然后递归的访问当前结点的左子树，最后递归的访问的访问当前结点的右子树
    @Override
    public void preorder(){
        preorder(root);
    }

    protected void preorder(TreeNode<E> root){
        if(root == null)
            return;
        System.out.print(root.element + "");//根
        preorder(root.left);//左
        preorder(root.right);//右
    }

    //6.获取书中元素的数量
    @Override
    public int getSize(){
        return size;
    }

    //7.返回根结点
    @Override
    public TreeNode<E> getRoot(){
        return root;
    }

    //8.删除一个元素
    //首先要确定元素的位置，然后在删除该元素以及重新连接树前要考虑两种情况
    //1>要删除的结点没有左子结点
    //       删除元素之后将该元素结点的父结点和该结点的右子结点连接起来就可以啦

    //2>要删除的结点有左子结点
    //       1、让rightMost指向current结点的左子树中包含最大元素的结点；
    //       2、parentOfRightMost指向rightMost结点的父结点；
    //       【rightMost结点不能有右子结点，但可能会有左子结点】
    //       3、使用rightMost结点中的元素值替换current结点中的元素值
    //       4、将parentOfRightMost结点和rightMost结点的左子结点连接
    //       5、删除rightMost结点

    @Override
    public boolean delete(E e){
        //**********************找到要删除的结点，current指向要删除的结点*************
        TreeNode<E> parent = null;
        TreeNode<E> current =root;

        while(current!=null){
            if(c.compare(e,current.element) <0){
                parent = current;
                current = current.right;
            }
            else{
                break;
            }
        }

        //如果该结点不在这棵树里的话
        if(current == null){
            return false;
        }
        //*********************************************************************

        //***********************************************************************
        //情况1：要删除的结点没有左子结点
        if(current.left==null){//current结点没有左子结点
            if(parent == null){//如果父结点是空的话
                root=current.right;//将current的右子结点赋给root结点
            }
            else{//否则的话，根据current是parent的左子结点还是右子结点
                if(c.compare(e,parent.element)<0){
                    parent.left = current.right;
                }
                else{
                    parent.right = current.right;
                }
            }
        }

        //情况2：要删除的结点有左子结点
        else{

            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while(rightMost.right !=null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if(parentOfRightMost.right == rightMost){
                parentOfRightMost.right =rightMost.right;
            }
            else{
                parentOfRightMost.left =rightMost.left;
            }
        }
        size--;
        return true;
    }




}



//树结点
class TreeNode<E>{
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;

    public TreeNode(E e){
        element = e;
    }

}

