package message;

import java.util.Arrays;

public class FriendChatmsg  extends Message{

    private int userid;//用户id

    private int friendid;//好友的id

    //来区分消息的类型
    private String messagetype;//Text代指文本消息、File代指文件消息

    private String message; //用户和好友之间进行交流的消息

    //用来区分聊天的类型，群聊消息，还是好有个人消息
    //Group好友消息,Friend群消息;chattype一般情况下为好友消息
    private String chattype;

    //private File file;
    private byte[] file;

    private int fileSize;//文件大小

    private int start;//开始的部分

    private int onceSize; //传一次的大小

    private String path; //路径

    private String fileName;//文件名称

    private String a; //加信息的


    private int Group=0;
    private int cishu;//记录发消息是第几次

    public FriendChatmsg(){

    }


    public FriendChatmsg(int userid,int friendid,byte[] file,String messagetype){
        this.userid=userid;
        this.friendid=friendid;
        this.file=file;
        this.messagetype=messagetype;
    }

    public  FriendChatmsg(int userid,int friendid,String message,String messagetype){
        this.userid=userid;
        this.friendid=friendid;
        this.message=message;
        this.messagetype=messagetype;

    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setFriendid(int friendid) {
        this.friendid = friendid;
    }

    public int getFriendid() {
        return friendid;
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

    public void setChattype(String chattype) {
        this.chattype = chattype;
    }

    public String getChattype() {
        return chattype;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public byte[] getFile() {
        return file;
    }


    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public void setOnceSize(int onceSize) {
        this.onceSize = onceSize;
    }

    public int getOnceSize() {
        return onceSize;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    @Override
    public String toString() {
        return "好友消息FriendChatmsg{" +
                "userid=" + userid +
                ", friendid=" + friendid +
                ", messagetype='" + messagetype + '\'' +
                ", message='" + message + '\'' +
                ", chattype='" + chattype + '\'' +
                ", file=" + Arrays.toString(file) +
                ", fileSize=" + fileSize +
                ", start=" + start +
                ", onceSize=" + onceSize +
                ", path='" + path + '\'' +
                ", fileName='" + fileName + '\'' +
                ", a='" + a + '\'' +
                ", Group=" + Group +
                ", cishu=" + cishu +
                '}';
    }
}
