package message;

public class Informationmsg extends Message{
    int userid;
    int friendid;


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
    public String toString(){
        return "userid = "+userid+", friendid = "+friendid;
    }
}
