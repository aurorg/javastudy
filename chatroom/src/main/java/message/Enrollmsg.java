package message;

//注册时客户端向服务端发的消息
public class Enrollmsg extends Message {

    private int userid; //用户的id
    private String name; //用户姓名
    private String password; //用户密码
    private int phonenumber; //用户的电话号码


    public Enrollmsg(){

    }

    public Enrollmsg( int phonenumber,String name,String password ){
       // this.userid=userid;
        this.phonenumber=phonenumber;
        this.name=name;
        this.password=password;
       // this.phonenumber=phonenumber;
    }

//    public void setPhonenumber(int phonenumber){
//        this.phonenumber=phonenumber;
//    }

//    public int getUserid(){
//        return this.userid;
//    }

    public int getPhonenumber(){
        return this.phonenumber;
    }
    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

//    @Override
//    public int getMessageType(){
//
//        return 0;
//    }


    public String toString(){
        //return "用户userid=" + userid  +  " ,用户name=" + name + " ,用户password=" + password + " ，用户phonenumber=" +phonenumber;

         return " 用户phonenumber=" +phonenumber +" ,用户name=" + name +" ,用户password=" + password;
    }



}
