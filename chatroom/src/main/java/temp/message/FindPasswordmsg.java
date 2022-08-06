package temp.message;

import message.Message;

public class FindPasswordmsg extends Message {

    private int userid;
    private int phonenumber;

    public FindPasswordmsg(int userid,int phonenumber){
        this.userid=userid;
        this.phonenumber=phonenumber;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    @Override
    public String toString() {
        return "找回密码：FindPasswordmsg{" + "userid=" + userid + ", phonenumber=" + phonenumber + '}';
    }
}
