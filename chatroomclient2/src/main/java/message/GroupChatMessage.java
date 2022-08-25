package message;

import java.io.File;

public class GroupChatMessage extends Message{


    private int userid;//用户id

    private int groupid;//好友的id

    //来区分消息的类型
    private String messagetype;//Text代指文本消息、File代指文件消息

    private String message; //用户和好友之间进行交流的消息

    //用来区分聊天的类型，群聊消息，还是好有个人消息
    //Group好友消息,Friend群消息;chattype一般情况下为好友消息
    private String chattype;

    private File file;

    private int count =1;//未读消息的条数


    private String a; //加信息的

    private int Group=0;
    private int cishu;//记录发消息是第几次

    public GroupChatMessage(int userid,int groupid,String message,String messagetype){
        this.userid=userid;
        this.groupid=groupid;
        this.message=message;
        this.messagetype=messagetype;
    }

    public GroupChatMessage(int userid,int groupid,File file,String messagetype){
        this.userid=userid;
        this.groupid=groupid;
        this.file=file;
        this.messagetype=messagetype;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    public int getUserid() {
        return userid;
    }


    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }
    public int getGroupid() {
        return groupid;
    }


    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }
    public String getMessagetype() {
        return messagetype;
    }

    public void setFile(File file) {
        this.file = file;
    }
    public File getFile() {
        return file;
    }

    public void setChattype(String chattype) {
        this.chattype = chattype;
    }
    public String getChattype() {
        return chattype;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public String toString(){
        return "userid = "+userid+", groupid = "+groupid+" message = "+message;
    }

}
