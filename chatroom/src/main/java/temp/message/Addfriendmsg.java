package temp.message;

import message.Message;

import java.util.List;

public class Addfriendmsg extends Message {
    private int userid;
    private List<Integer> friendidlist;
    private int friendid;
    private boolean setList=false;



    public Addfriendmsg(){}

    public Addfriendmsg(int userid,int friendid){
        this.userid=userid;
        this.friendid=friendid;
    }

    public Addfriendmsg(int userid,List<Integer> friendidlist){
        this.userid=userid;
        this.friendidlist=friendidlist;
    }

    public void setSetList(boolean setList) {
        this.setList = setList;
    }

    public boolean getSetList() {
        return setList;
    }

    public int getUserid(){return this.userid;}
    public int getFriendid(){return this.friendid;}

    public List<Integer> getFriendIDList() {
        return friendidlist;
    }


    public String toString(){
        return "userid = "+userid+", friendid = "+friendid;
    }
}
