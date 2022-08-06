package temp.message;

import message.Message;

public class SendApplyMessage extends Message {
    private int userid;
    private int friendid;
    private int groupid=0;
    private String message;
    public String chattype="FRIEND";
    public SendApplyMessage(int userid,int friendid,String message){
        this.userid=userid;
        this.friendid=friendid;
        this.message=message;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public void setChattype(String chattype) {
        chattype = chattype;
    }

    public String getChattype() {
        return chattype;
    }

    public int getUserid() {
        return userid;
    }

    public int getGroupid() {
        return groupid;
    }

    public int getFriendid() {
        return friendid;
    }

    public String getMessage() {
        return message;
    }
    public String toString(){
        return "userid =" + userid + ",friendid =" + friendid + " , message= " + message;
    }
}
