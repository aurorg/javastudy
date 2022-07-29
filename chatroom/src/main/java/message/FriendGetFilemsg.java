package message;

public class FriendGetFilemsg extends Message {
    private int userid;
    private int friendid;
    private boolean refuse=false;
    public FriendGetFilemsg(){}
    public FriendGetFilemsg(int userid,int Friendid){
        this.userid=userid;
        this.friendid=Friendid;
    }

    public void setRefuse(boolean refuse) {
        this.refuse = refuse;
    }

    public boolean isRefuse() {
        return refuse;
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
