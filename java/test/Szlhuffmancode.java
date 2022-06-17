/*import java.io.*;
import java.util.*;

interface MyTree<E>{
    Node createHuffmanTree(List<Node> nodes);
    void mirrorTree(Node root);
    void mirrorTree2(Node root);
    void preOrder(Node root);
    void mirrorTreeQueue(Node root);
    void widthTree(Node root);
    boolean CompleteTree(Node root);

}

public class Szlhuffmancode {
    public static void main(String[] args) {

        //测试压缩文件

//        String srcFile = "/home/shizhanli/luck.txt";
//        String dstFile = "/home/shizhanli/lucck.txt";
//
//       zipFile(srcFile,dstFile);
//       System.out.println("压缩好了");


        //测试解压文件
        String zipFile = "/home/shizhanli/lucck.txt";
        String dstFile = "/home/shizhanli/happy.txt";

        unZipFile(zipFile, dstFile);
        System.out.println("解压好了");


//        String content  = "linux group linux group is very good";
//        //得到字节数组
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length); //40；获得content字符串的长度
//
//        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
//        System.out.println("压缩后的结果：" + Arrays.toString(huffmanCodesBytes));
//
//        byte[] sourceBytes =decode(huffmanCodes,huffmanCodesBytes);
//        System.out.println("初始的=" + new String(sourceBytes));
//

        //1前序遍历看对不对
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println("nodes=" + nodes);

        //2测试创建的树
//        System.out.println("哈弗曼树");
//        Node huffmanTreeRoot = createHuffmanTree(nodes);
//        System.out.println("前序遍历");
//        huffmanTreeRoot.preOrder();

        //3测试是否有哈弗曼编码表:getCodes(huffmanTreeRoot,"",stringBuilder);//根结点没有左右，所以是空
        //简单调用
//        Map<Byte,String> huffmanCodes =getCodes(huffmanTreeRoot);
//        System.out.println("生成的哈夫曼编码表"+ huffmanCodes);

        //4压缩后测试
//        byte[] huffmanCodeBytes = zip(contentBytes,huffmanCodes);
//        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));//17个

    }


    //压缩的时候写一个方法将所有的方法封装起来，调用方便,传入原来的数组，返回压缩了的数组
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //1根据nodes创建哈弗曼树
        //AuroraTree<Node> huffmanTreeRoot =new AuroraTree<Node>.createHuffmanTree();
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //2.把0 1连起来成对应的哈弗曼编码（0，1）
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //3.进行压缩，得到压缩后的哈弗曼编码数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }


    //对文件进行压缩~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //传入压缩文件的完整路径String srcFile，压缩后将文件放到一个目录中String dstFile
    public static void zipFile(String srcFile, String dstFile) {

        //创建输入流读取文件
        FileInputStream is = null;
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建一个和文件大小相同的byte[]读数据;读取文件的哈夫曼编码表,对文件压缩;创建文件的输出流，存放压缩文件
        try {
            is = new FileInputStream(srcFile);

            //创建一个和文件大小相同的byte[]读数据
            byte[] b = new byte[is.available()];

            //读
            is.read(b);

            //读取文件的哈夫曼编码表,对文件压缩
            byte[] huffmanBytes = huffmanZip(b);

            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);

            //创建一个和文件输出流关联的对象流ObjectOutputStream
            oos = new ObjectOutputStream(os);

            //把哈弗曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //以对象流的方式写入哈弗曼编码，为了恢复文件时使用


            //把哈弗曼编码写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {//记得都要关闭
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //对文件进行解压~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //传入要解压的文件String zipFile，解压之后的位置String dstFile
    public static void unZipFile(String zipFile, String dstFile) {
        //创建输入流
        InputStream is = null;
        //对象输入流
        ObjectInputStream ois = null;
        //输出流
        OutputStream os = null;
        try {
            //创建输入流
            is = new FileInputStream(zipFile);
            //创建和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取哈夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //写入文件
            os = new FileOutputStream(dstFile);
            os.write(bytes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    //压缩deguocheng~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //将字符串对应的byte[]数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的字节数组byte[ ]
    //传入：bytes：原来的字符串对应的字节数组
    //     huffmanCodes生成的哈夫曼编码表map
    //返回：返回哈法曼树处理后（经过压缩）的byte数组
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCodes将bytes转换成哈弗曼树对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //System.out.println("测试stringBuilder=" +stringBuilder.toString());

        //将哈夫曼编码（01这种）转成byte[]字节数组，八位一个字节

        //统计返回byte[] huffmanCodeBytes的长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;  //位数不够8位
        }

        //创建存储压缩之后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个Byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            //将strByte转成一个byte，放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }


    //生成哈弗曼树对应的哈弗曼编码（左边0，右边1）
    //思路：
    //1.将哈弗曼树编码表存放在Map<Byte,String>中；eg:97(a)对应 01（01就是哈夫曼编码）
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //2.在生成哈夫曼编码表，需要拼接路径，也就是连起来的0，1，定义一个StringBuilder存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //简便调用方法，重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {//是空的时候
            return null;
        } else {
            //处理左边
            getCodes(root.left, "0", stringBuilder);
            //处理右边
            getCodes(root.right, "1", stringBuilder);

        }
        return huffmanCodes;
    }

    //将传入的node结点的所有叶子结点的哈弗曼编码存放在huffmanCodes中
    //node :传入结点
    //code： 路径就是0，1【左子结点0，右子节点1】
    //stringBuilder 用来拼接0，1
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code(0,1)加入到stringBuilder中之后连起来
        stringBuilder2.append(code);
        if (node != null) { //如果node == null ，不处理
            //判断当前node是叶子结点还是非叶子结点
            if (node.data == null) {//非叶子结点
                //需要递归处理，是非叶子结点表示还没有到头，需要继续下去

                //向左递归，0
                getCodes(node.left, "0", stringBuilder2);

                //向右递归，1
                getCodes(node.right, "1", stringBuilder2);

            } else {//不等于null，就表示是叶子结点，表示找到了叶子结点的尾部
                huffmanCodes.put(node.data, stringBuilder2.toString());//转成字符串

            }

        }
    }

//    //前序遍历方法
//    private static void preOrder(Node root) {
//        if (root != null) {
//            root.preOrder();
//        } else {
//            System.out.println("空树");
//        }
//    }


    //传进去：字节数组byte[] bytes
    //返回：List<Node>,形式【Node[date=97(数据）,weight = 5】
    private static List<Node> getNodes(byte[] bytes) {
        //创建ArrayLIst
        ArrayList<Node> nodes = new ArrayList<Node>();

        //遍历字节数组，统计并且存储每一个byte出现的次数：map
        Map<Byte, Integer> counts = new HashMap(); //Integer表述次数
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { //第一次的时候，Map中还没有存放字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);//不是第一次
            }
        }
        //把每个键值对转成一个Node对象，并且加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {//遍历map
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //解压~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //1.将huffmanCodeBytes转成哈夫曼编码（0，1）
    //2.将哈弗曼编码（0，1）变成开始的字符串

    //1.传入huffmanCodeBytes，返回哈夫曼编码
    private static String byteToBitString(boolean flag, byte t) {
        //用临时变量保持t,并且转成int类型
        int temp = t;
        //正数不够8位，需要补位,用布尔值判断,最后一位不用补
        if (flag) {
            //用按位huo补位
            temp |= 256;
        }
        //返回哈弗曼编码二进制对应的补码
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);//取后8位
        } else {//负数就直接返回
            return str;
        }
    }


    //传入哈弗曼编码（map）,哈夫曼编码转化的字节数组，返回字符串对应的数组
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.byte[] huffmanBytes转换成哈弗曼树编码的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte t = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, t));
        }

        //把字符串按照哈夫曼编码进行解码，把哈夫曼编码表反过来
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {//quchulai
            map.put(entry.getValue(), entry.getKey());//把键值对变成值键对，反过来
        }
        //System.out.println("map=" + map);
        // return null;
        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();//list存放字符
        for (int i = 0; i < stringBuilder.length(); ) { //不能写i++,因为i随c变化
            int c = 1;//计数器
            boolean flag = true;
            Byte temp = null;

            while (flag) {
                String key = stringBuilder.substring(i, i + c);//i在原来的位置，c向后移，匹配到一个01字符串就停止
                temp = map.get(key);
                if (temp == null) {//没有匹配
                    c++;
                } else {
                    flag = false;
                }
            }
            list.add(temp);
            i += c;
        }
        //把list的字符串放入byte放回
        byte t[] = new byte[list.size()];
        for (int i = 0; i < t.length; i++) {
            t[i] = list.get(i);
        }
        return t;
    }


    //通过list创建哈弗曼树~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //传进去：nodes
    //返回：根节点
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);//进行排序，从小到大
            //取最小的
            Node leftNode = nodes.get(0);
            //取出第二小
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树，根节点没有data，只有权值，数据存在叶子结点
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //删除处理过的数据
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将新的二叉树加入到node中
            nodes.add(parent);

        }
        //返回根节点
        return nodes.get(0);
    }

    abstract class AuroraTree<E> implements MyTree<E> {
        //建树
        public Node createHuffmanTree(List<Node> nodes) {
            while (nodes.size() > 1) {
                Collections.sort(nodes);//进行排序，从小到大
                //取最小的
                Node leftNode = nodes.get(0);
                //取出第二小
                Node rightNode = nodes.get(1);
                //创建一颗新的二叉树，根节点没有data，只有权值，数据存在叶子结点
                Node parent = new Node(null, leftNode.weight + rightNode.weight);
                parent.left = leftNode;
                parent.right = rightNode;

                //删除处理过的数据
                nodes.remove(leftNode);
                nodes.remove(rightNode);

                //将新的二叉树加入到node中
                nodes.add(parent);

            }
            //返回根节点
            return nodes.get(0);
        }

        //是否是一颗完整的树
        public boolean CompleteTree(Node root){
            if(root !=null)
                return true;
            else
                return false;
        }
        //前序遍历方法
        public void preOrder(Node root) {
            if (root != null) {
                root.preOrder();
            } else {
                System.out.println("空树");
            }
        }
        //树的镜像（递归）
        public void mirrorTree(Node root) {
            if (root == null) return;

            //交换该节点指向的左右结点
            Node temp = root.left;
            root.left = root.right;
            root.right = temp;

            //对其左右进行镜像处理
            mirrorTree(root.left);
            mirrorTree(root.right);
        }

        //树的镜像（非递归：层次遍历）
        public void mirrorTree2(Node root) {
            Node temp;
            temp = root.right;
            root.right = root.left;
            root.left = temp;
        }

        public void mirrorTreeQueue(Node root) {
            if (root == null)
                return;
            //如果树为 null 直接返回。否则将根节点入队列
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            while (!queue.isEmpty()) {
                //队列不为空时，节点出队，交换该节点的左右子树
                Node root1 = queue.poll();
                mirrorTree2(root);
                if (root1.right != null) {
                    queue.add(root1.right);
                    //如果左子树不为 null 入队
                }
                if (root1.left != null) {
                    queue.add(root1.left);
                    //如果右子树不为 null 入队
                }
            }
        }

        //层序遍历树（广度优先搜索）
        public void widthTree(Node root) {
            if (root == null) return;

            Queue<Node> q = new ArrayDeque<Node>();
            q.add(root);
            Node c;

            while (!q.isEmpty()) {
                c = q.peek();
                System.out.print(c.data + " ");
                if (c.left != null) {
                    q.add(c.left);
                }
                if (c.right != null) {
                    q.add(c.right);
                }
                q.poll();
            }
        }
    }
}

//创建结点，放数据和权值~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class Node implements Comparable<Node>{
    Byte data;//存放数据本身，eg:a是97，b是98
    int weight;//权值，表示字符出现的次数
    Node left;//左边
    Node right;//右边
    public Node(Byte data,int weight){ //构造器
        this.data = data;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node o){
        return this.weight - o.weight;//从小到大排序
        //return -(this.weight - o.weight);从大到小排序
    }

    public String toString() {
        return "Node [data = " + data + "  weight = " + weight + "]";
    }//打印结点信息

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

}

 */

