/*
package com.hulala.huffmantree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class H2 {
    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 6, 30, 1 };

        Node root = createHuffmanTree(arr);

        preOrder(root);  //测试一下
    }
    //编写一个前序遍历的方法
    public static void preOrder(Node root) {
        if(root != null) {
            root.preOrder();
        }else{
            System.out.println("不能遍历，因为这棵树是空树");
        }
    }
// 创建赫夫曼树的方法

    // @param arr 需要创建成哈夫曼树的数组
    // @return 创建好后的赫夫曼树的 root 结点

    public static Node createHuffmanTree(int[] arr) {
// 第一步为了操作方便（因为数组不支持排序）
// 1. 遍历 arr 数组
// 2. 将 arr 的每个元素构成成一个 Node
// 3. 将 Node 放入到 ArrayList 中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));//把每个元素变成node
        }
//整个过程是一个循环的过程
        while(nodes.size() > 1) {  //当最后只剩下最后一个元素的时候就不循环了
//排序 从小到大
            Collections.sort(nodes);//使用集合排序
                                    //可以把nodes放进去的原因是Node实现 Comparable 接口
            System.out.println("nodes =" + nodes);//输出排序的结果
//取出根节点权值最小的两颗二叉树
//(1) 取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0); //因为已经排过顺序，所以第一个是最小的
//(2) 取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);
//(3)构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
//(4)从 ArrayList 删除处理过的二叉树（删除掉原来用过的）
            nodes.remove(leftNode);
            nodes.remove(rightNode);
//(5)将 parent 加入到 nodes
            nodes.add(parent);
        }
//返回哈夫曼树的 root 结点
        return nodes.get(0);
    }
}
// 创建结点类
// 为了让 Node 对象支持排序 Collections 集合排序
// 让 Node 实现 Comparable 接口
class Node implements Comparable<Node> {
    int value; // 结点权值
    char c;//字符
    Node left; // 指向左子结点
    Node right; // 指向右子结点
    //写一个前序遍历
    public void preOrder() {
        System.out.println(this);
        if(this.left != null) {
            this.left.preOrder();//遍历左边
        }
        if(this.right != null) {
            this.right.preOrder();//遍历右边
        }
    }
    public Node(int value) {  //构造器
        this.value = value;
    }
    @Override
    public String toString() {  //方法重写
        return "Node [value= " + value + "]";
    }
    @Override
    public int compareTo(Node o) {
// （对权值进行比较，左小右大）
        return this.value - o.value;//表示从小到大排序
        //return -(this.value -o.value);//表示从大到小排序
    }
}
*/
