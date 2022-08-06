package temp.message;

import message.Message;

public class GroupDeleteAdministratorMessage extends Message {
    int userid;
    int groupid;
    int peopleid;

    public GroupDeleteAdministratorMessage(int userid,int groupid,int peopleid){
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
        return "删除管理员：GroupDeleteAdministratorMessage{" + "userid=" + userid + ", groupid=" + groupid + ", peopleid=" + peopleid + '}';
    }
}
