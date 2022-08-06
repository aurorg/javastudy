package temp.message;

import message.Message;

import java.io.File;

public class FriendGetFilemsg extends Message {
    private int userid;
    private int friendid;

    //来区分消息的类型
    private String messagetype;//Text代指文本消息、File代指文件消息

    private String message; //用户和好友之间进行交流的消息

    //用来区分聊天的类型，群聊消息，还是好有个人消息
    //Group好友消息,Friend群消息;chattype一般情况下为好友消息
    private String chattype;

    private File file;



    private boolean refuse=false;
    public FriendGetFilemsg(){}

    public FriendGetFilemsg(int userid,int Friendid,String message){
        this.userid=userid;
        this.friendid=Friendid;
        this.message=message;
    }

    public void setMessage(String message){
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }

    public void setFile(File file){
        this.file=file;
    }
    public File getFile(){
        return file;
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
        return "userid = "+userid+", friendid = "+friendid+" message = "+message;
    }

}
