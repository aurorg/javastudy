package message;

public class GroupAuthenticationMessage extends Message{
    int userid;
    int groupid;

    public GroupAuthenticationMessage(int userid,int groupid){
        this.userid=userid;
        this.groupid=groupid;
    }
//——————————————————————————————————————————————————————————————————————
    public void setUserid(int userid){
        this.userid=userid;
    }

    public int getUserid(){
        return userid;
    }
//——————————————————————————————————————————————————————————————————————
    public void setGroupid(int groupid){
        this.userid=userid;
    }

    public int getGroupid(){
        return groupid;
    }
//———————————————————————————————————————————————————————————————————————


    @Override
    public String toString() {
        return "验证身份消息GroupAuthenticationMessage{" + "userid=" + userid + ", groupid=" + groupid + '}';
    }

}
