package message;

public class Logoutmsg extends Message {
    private int userid; //用户的id
//    private String name; //用户姓名
//    private String password; //用户密码
//    private int phonenumber; //用户的电话号码


    public Logoutmsg(){

    }
    public Logoutmsg(int userid){
        this.userid=userid;

    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid(){
        return this.userid;
    }

//    public String getName(){
//        return this.name;
//    }
//
//    public String getPassword(){
//        return this.password;
//    }
//
//    public int getPhonenumber(){
//        return this.phonenumber;
//    }

    public String toString(){
        return "用户userid=" + userid;
    }


}




