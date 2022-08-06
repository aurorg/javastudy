package temp.message;

import message.Message;

public class Informationfriendunreadmsg extends Message {
    private int userid;
    private int friendid;
    private String message;

    public Informationfriendunreadmsg(int userid,int friendid){
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

