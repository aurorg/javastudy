package message;

public class GroupAuthenticationMessage extends Message{
    int userid;
    int groupid;
    int memberidentity;

    public GroupAuthenticationMessage(int userid,int groupid,int memberidentity){
        this.userid=userid;
        this.groupid=groupid;
        this.memberidentity=memberidentity;
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
        this.groupid=groupid;
    }

    public int getGroupid(){
        return groupid;
    }
//———————————————————————————————————————————————————————————————————————

    public void setMemberidentity(int memberidentity){
        this.memberidentity=memberidentity;
    }

    public int getMemberidentity(){
        return memberidentity;
    }

//———————————————————————————————————————————————————————————————————————
    @Override
    public String toString() {
        return "验证身份消息GroupAuthenticationMessage{" + "userid=" + userid + ", groupid=" + groupid + '}';
    }

}
