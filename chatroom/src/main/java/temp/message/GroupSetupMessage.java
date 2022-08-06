package temp.message;

import message.Message;

public class GroupSetupMessage extends Message {
    int userid;
    String groupname;

    public GroupSetupMessage(int userid,String groupname){
        this.userid=userid;
        this.groupname=groupname;
    }
    //——————————————————————————————————————————————————————————————————————
    public void setUserid(int userid){
        this.userid=userid;
    }

    public int getUserid(){
        return userid;
    }
    //——————————————————————————————————————————————————————————————————————
    public void setGroupname(String groupname){
        this.groupname=groupname;
    }

    public String getGroupname(){
        return groupname;
    }
//———————————————————————————————————————————————————————————————————————

    @Override
    public String toString() {
        return "建群的GroupSetupMessage{" + "userid=" + userid + ", groupname='" + groupname + '}';
    }
}
