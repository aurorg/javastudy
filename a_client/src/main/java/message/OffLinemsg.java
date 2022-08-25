package message;

public class OffLinemsg extends Message{
    private int userid; //用户的id

    public OffLinemsg(){

    }
    public OffLinemsg(int userid){
        this.userid=userid;

    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid(){
        return this.userid;
    }


    @Override
    public String toString() {
        return "下线的：OffLinemsg{" + "userid=" + userid + '}';
    }
}
