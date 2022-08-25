package message;

public class GroupListMemberMessage extends Message{

    int userid;
    int groupid;


    public GroupListMemberMessage(int userid, int groupid ){
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
        this.groupid=groupid;
    }

    public int getGroupid(){
        return groupid;
    }
//———————————————————————————————————————————————————————————————————————

//————————————————————————————————————————————————————————————————————————


    @Override
    public String toString() {
        return "群列表成员的消息GroupListMemberMessage{" + "userid=" + userid + ", groupid=" + groupid + '}';
    }
}
