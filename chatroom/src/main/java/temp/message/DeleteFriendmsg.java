package temp.message;

import message.Message;

public class DeleteFriendmsg extends Message {
    private int userid;
    private int friendid;
    public DeleteFriendmsg(){}
    public DeleteFriendmsg(int userid,int friendid){
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
