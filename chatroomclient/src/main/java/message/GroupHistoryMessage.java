package message;

public class GroupHistoryMessage extends Message{
    int userid;
    int groupid;


    public GroupHistoryMessage(int userid, int groupid ){
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
        return "群历史消息：GroupHistoryMessage{" + "userid=" + userid + ", groupid=" + groupid + '}';
    }
}
