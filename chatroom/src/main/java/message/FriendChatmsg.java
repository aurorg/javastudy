package message;

public class FriendChatmsg  extends Message{

    private int userid;//用户id

    private int friendid;//好友的id

    private String messagetype;//T代指文本消息、F代指文件消息

    private String message; //用户和好友之间进行交流的消息

    //用来区分群消息和个人消息
    //G好友消息,F群消息;chattype一般情况下为好友消息
    private String chattype;

    private int Group=0;
    private int cishu;//记录发消息是第几次

    public  FriendChatmsg(){}

    public  FriendChatmsg(int userid,int friendid,String message,String messagetype){
        this.userid=userid;
        this.friendid=friendid;
        this.message=message;
        this.messagetype=messagetype;

    }


    public void setChattype(String chattype) {
        this.chattype = chattype;
    }

    public void setGroup(int group) {
        Group = group;
    }

//    public void setCishu(int cishu){
//        cishu=cishu;
//    }

    public String getChattype() {
        return chattype;
    }

    public int getGroup() {
        return Group;
    }

    public String getMessagetype() {
        return messagetype;
    }

    public String getMessage(){
        return this.message;
    }
    public int getFriendid(){
        return this.friendid;
    }
    public int getUserid(){
        return this.userid;
    }
//    public int getCishu(){
//        return this.cishu;
//    }

    /*    @Override
        public int getMessageType() {
            return FriendChatRequestMessage;
        }*/
    public String toString(){
        return "userid = "+userid+", friendid = "+friendid+" message = "+message;
    }


}
