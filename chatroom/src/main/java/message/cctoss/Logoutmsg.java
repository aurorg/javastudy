package message.cctoss;

import message.Message;

public class Logoutmsg extends Message {
    private int userid; //用户的id
    private String name; //用户姓名
    private String password; //用户密码
    private int phonenumber; //用户的电话号码


    public Logoutmsg(){

    }
    public Logoutmsg(int userid,String name,String password,int phonenumber){
        this.userid=userid;
        this.name=name;
        this.password=password;
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
