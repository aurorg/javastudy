package temp.message;

import message.Message;

public class FriendListmsg extends Message {
    private int userid;
    public FriendListmsg(){}
    public FriendListmsg(int userid){
        this.userid=userid;
    }

    public int getUserid() {
        return userid;
    }


    public String toString(){
        return "userid = "+userid;
    }
}
