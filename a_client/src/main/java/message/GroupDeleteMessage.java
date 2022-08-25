package message;

public class GroupDeleteMessage extends Message{
    int userid;
    int groupid;
   // String groupname;

    public GroupDeleteMessage(int userid,int groupid){
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


    @Override
    public String toString() {
        return "解散群的GroupDeleteMessage{" + "userid=" + userid + ", groupid=" + groupid + '}';
    }
}
