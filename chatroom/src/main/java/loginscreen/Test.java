package loginscreen;

public class Test {

}
/**
 *   //用户登录函数
 *     public static void enroll(){
 *
 *         System.out.println("请输入您的账号名称:");
 *         String name=input.next();
 *
 *         System.out.println("请输入您的密码:");
 *         String password1 =input.next();
 *
 *
 *         //检索数据库看这个用户的数据是否在数据库中
 *         try {
 *
 *             //注册JDBC驱动
 *             Class.forName(JDBC_DRIVER);
 *
 *             //获得数据库链接
 *             //System.out.println("连接数据库.....");
 *             conn = DriverManager.getConnection(DB_URL, USER, PASS);
 *
 *             //执行查询
 *             //System.out.println("实例化Statement对象...");
 *
 *             //创建传输器
 *             stat = conn.createStatement(); //createStatement()：创建向数据库发送sql的statement对象。
 *
 *
 *             String sql;
 *             sql = "SELECT username,userpassword FROM usermsg";
 *
 *             //传输sql并且返回结果
 *             ResultSet rs = stat.executeQuery(sql); //executeQuery(String sql) ：用于向数据发送查询语句
 *
 *             //展开结果集数据库
 *             //next()会将光标向下移动一行，
 *             //并返回当前行是否有效，如果遍历完成整个表，则会返回false
 *
 *             boolean isexit =false; //临时变量，判断该用户是否存在
 *
 *             while (rs.next()) {
 *
 *                 // 通过字段检索
 *                 String username = rs.getString("username");
 *                 String userpassword=rs.getString("userpassword");
 *
 *                 if(username.equals(name) ==true && userpassword.equals(password1)==true){
 *                      isexit =true;
 *                 }
 * //                else {
 * //                    System.out.println("抱歉，您的用户不存在，您可以选择注册用户");
 * //                }
 *             }
 *
 *             //判断之后进行后续选择
 *             if(isexit){
 *                 System.out.println("您的账户存在，您可以选择后续操作");
 *                 login();
 *             }else{
 *                 System.out.println("抱歉，您的账户不存在，您可以选择注册账户");
 *                 login();
 *             }
 *
 *
 *             // 完成后关闭
 *             rs.close();
 *             stat.close();
 *             conn.close();
 *
 *         } catch (SQLException se) {
 *             // 处理 JDBC 错误
 *             se.printStackTrace();
 *         } catch (Exception e) {
 *             // 处理 Class.forName 错误
 *             e.printStackTrace();
 *         } finally {
 *             // 关闭资源
 *             try {
 *                 if (stat != null) stat.close();
 *             } catch (SQLException se2) {
 *             }// 什么都不做
 *             try {
 *                 if (conn != null) conn.close();
 *             } catch (SQLException se) {
 *                 se.printStackTrace();
 *             }
 *
 *         }
 *
 *
 *     }
 *
 *
 *
 *     //用户注册函数
 *     public static void login(){
 *
 *
 *     }
 *
 *     //用户注销
 *     public static void logout(){
 *
 *     }
 *
 */
