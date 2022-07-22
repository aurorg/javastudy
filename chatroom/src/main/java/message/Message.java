package message;

import java.util.HashMap;
import java.util.Map;

public class Message {
    //private int msgtype;

    private int messageType;
    private int sequenceId;
    //public  int getMessageType();


    //第一部分：注册，登录，注销，退出界面

    private static final int enrollmsg =0;   //【注册】    客户端发给服务端的消息
    private static final int enrollmsg1 =1;  //【注册】    服务端向客户端发的消息

    private static final int loginmsg =3;    //【登录】    客户端发给服务端的消息
    private static final int loginmsg1 =4;   //【登录】    服务端向客户端发的消息

    private static final int logoutmsg =5;   //【注销】    客户端发给服务端的消息
    private static final int logoumsg1 =6;   //【注销】    服务端向客户端发的消息

    private static final int quit = 7;        //【退出系统】    客户端发给服务端的消息
    private static final int quit1 =8;        //【退出系统】    服务端向客户端发的消息

    private static final Map<Integer,Class<?>> messageClasses = new HashMap<>();

    public static Class<?> getMessageClass(int messageType){
        return messageClasses.get(messageType);
    }
    public int getSequenceId(){
        return sequenceId;
    }


}
