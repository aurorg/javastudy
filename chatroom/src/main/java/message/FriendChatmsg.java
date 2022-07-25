package message;

public class FriendChatmsg  extends Message{
    private int userid;
    private int friendid;

    private String messagetype;//T代指文本消息、F代指文件消息

    private String message;

    private String chattype;//G消息来自群组,F消息来自个人 //chattype默认为F=friend
    private int Group=0;
    public  FriendChatmsg(){}
    public  FriendChatmsg(int userID,int FriendId,String message,String msg_type){
        this.userid=userid;
        this.friendid=friendid;
        this.message=message;
        this.messagetype=msg_type;
    }


    public void setChattype(String chattype) {
        this.chattype = chattype;
    }

    public void setGroup(int group) {
        Group = group;
    }

    public String getChattype() {
        return chattype;
    }

    public int getGroup() {
        return Group;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public String getMessage(){return this.message;}
    public int getFriendId(){return this.FriendId;}
    public int getUserID(){return this.userID;}
    /*    @Override
        public int getMessageType() {
            return FriendChatRequestMessage;
        }*/
    public String toString(){
        return "userID = "+userID+", FriendId = "+FriendId+" message = "+message;
    }


}
