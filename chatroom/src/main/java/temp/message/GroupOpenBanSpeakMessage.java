package temp.message;

import message.Message;

public class GroupOpenBanSpeakMessage extends Message {
    int userid;
    int groupid;


    public GroupOpenBanSpeakMessage(int userid,int groupid){
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
        return "开启禁言模式的：GroupOpenBanSpeakMessage{" + "userid=" + userid + ", groupid=" + groupid + '}';
    }
}
