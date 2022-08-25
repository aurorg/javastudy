package message;

public class Loginmsg extends Message {
    private int userid; //用户的id
//    private String name; //用户姓名
    private String password; //用户密码
//    private int phonenumber; //用户的电话号码


    public Loginmsg(){

    }
    public Loginmsg(int userid,String password){
        this.userid=userid;
        this.password=password;

    }

    public int getUserid(){
        return this.userid;
    }

//    public String getName(){
//        return this.name;
//    }
//
    public String getPassword(){
        return this.password;
    }

//
//    public int getPhonenumber(){
//        return this.phonenumber;
//    }

    public String toString(){
        return "用户userid=" + userid + "用户密码password = " + password;
    }


    }

