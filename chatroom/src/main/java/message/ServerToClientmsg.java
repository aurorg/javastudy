package message;

import message.Message;

public class ServerToClientmsg extends Message {
    private boolean success;
    private String reason;
    private int ServerToClientmsg;
    int MessageType=ServerToClientmsg;
    
    public ServerToClientmsg(){
        
    }
    public ServerToClientmsg(boolean success,String reason){
        this.success=success;
        this.reason=reason;
    }
    public void setMessageType(int MessageType){
        this.MessageType=MessageType;
    }
    public boolean getSuccess(){
        return this.success;
    }
    public String getReason(){
        return this.reason;
    }
    public int getMessageType() {
        return this.MessageType;
    }
    public String toString(){
        return "是否成功 = "+success+", 原因 = "+reason;
    }
}
