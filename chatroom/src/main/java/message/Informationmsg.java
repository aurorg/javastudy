package message;

public class Informationmsg extends Message{
    private int userid;
    private int friendid;
    private String message;


    public Informationmsg(int userid,int friendid){
        this.friendid=friendid;
        this.userid=userid;
    }

    public int getUserid() {
        return userid;
    }

    public int getFriendid() {
        return friendid;
    }

    public String getMessage(){
        return message;
    }

    public String toString(){
        return "userid = "+userid+", friendid = "+friendid;
    }
}
