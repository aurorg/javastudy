package message;

public class ShieldFriendmsg extends Message{

    private int userid;
    private int friendid;
    public ShieldFriendmsg(){}
    public ShieldFriendmsg(int userid,int friendid){
        this.userid=userid;
        this.friendid=friendid;
    }

    public int getUserid() {
        return userid;
    }

    public int getFriendid() {
        return friendid;
    }

    public String toString(){
        return "userid = "+userid+", friendid = "+friendid;
    }
}

