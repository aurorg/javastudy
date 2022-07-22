package message.cctoss;

import message.Message;

//注册时客户端向服务端发的消息
public class Enrollmsg extends Message {
    private int userid; //用户的id
    private String name; //用户姓名
    private String password; //用户密码
    private int phonenumber; //用户的电话号码


    public Enrollmsg(){

    }

//    public Enrollmsg(int userid,String name,String password,int phonenumber){
//        this.userid=userid;
//        this.name=name;
//        this.password=password;
//        this.phonenumber=phonenumber;
//    }

    public void setPhonenumber(int phonenumber){
        this.phonenumber=phonenumber;
    }

    public int getUserid(){
        return this.userid;
    }

    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

    public int getPhonenumber(){
        return this.phonenumber;
    }

    public String toString(){
        return "用户userid=" + userid  +  " ,用户name=" + name + " ,用户password=" + password + " ，用户phonenumber=" +phonenumber;
    }



}
