package message;

public class GroupDeleteMemberMessage extends Message{
    int userid;
    int groupid;
    int peopleid;

    public GroupDeleteMemberMessage(int userid,int groupid,int peopleid){
        this.userid=userid;
        this.groupid=groupid;
        this.peopleid=peopleid;
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


    public void setPeopleid(int peopleid) {
        this.peopleid = peopleid;
    }

    public int getPeopleid() {
        return peopleid;
    }
//———————————————————————————————————————————————————————————————————————


    @Override
    public String toString() {
        return "将用户踢出群：GroupDeleteMemberMessage{" + "userid=" + userid + ", groupid=" + groupid + ", peopleid=" + peopleid + '}';
    }
}
