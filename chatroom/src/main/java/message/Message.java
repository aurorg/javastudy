package message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract  class Message  implements Serializable {
    //private int msgtype;

    private int messageType;
    private int sequenceId;
    //public abstract int getMessageType();


    //第一部分：注册，登录，注销，退出界面

    public static final int Enrollmsg =0;   //【注册】    客户端发给服务端的消息
    private static final int Enrollmsg1 =1;  //【注册】    服务端向客户端发的消息

    public static final int Loginmsg =2;    //【登录】    客户端发给服务端的消息
    private static final int Loginmsg1 =3;   //【登录】    服务端向客户端发的消息

    public static final int Logoutmsg =4;   //【注销】    客户端发给服务端的消息
    private static final int Logoutmsg1 =5;   //【注销】    服务端向客户端发的消息

    private static final int Quit = 6;        //【退出系统】    客户端发给服务端的消息
    private static final int Quit1 =7;        //【退出系统】    服务端向客户端发的消息

    public static final int FriendChatmsg =8;  //好友聊天







    //
//    private static final Map<Integer,Class<?>> messageClasses = new HashMap<>();
//
//    public static Class<?> getMessageClass(int messageType){
//        return messageClasses.get(messageType);
//    }
    public int getSequenceId(){
        return sequenceId;
    }
//    static{
//        messageClasses.put(0, message.Enrollmsg.class);
//        //messageClasses.put(1, message.Enrollmsg1.class);
////        messageClasses.put(2, message.Loginmsg.class);
////        messageClasses.put(3, message.Loginmsg1.class);
////        messageClasses.put(4, message.Logoutmsg.class);
////        messageClasses.put(5, message.Logoumsg1.class);
////        messageClasses.put(6, message.Quit.class);
////        messageClasses.put(7, message.Quit1.class);
////        messageClasses.put(8, message.Enrollmsg.class);
//
//    }

}
