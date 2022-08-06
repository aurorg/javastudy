package common;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

public final class ChatHandlerMap {

    private static ConcurrentHashMap<Integer, Channel> mapToChannel=new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Channel,Integer> mapToUser=new ConcurrentHashMap<>();

    //用键值对将用户和handler绑定起来

    public static void add(int userid,Channel chathandler){
        mapToChannel.put(userid,chathandler);
        mapToUser.put(chathandler,userid);
    }
    public static Channel getChannel(int userid){
        if(mapToChannel.containsKey(userid)){
            return mapToChannel.get(userid);
        }else{
            return null;
        }
    }

    public static int getUser(Channel channel){
        if(mapToUser.containsKey(channel)){
            return mapToUser.get(channel);
        }else{
            return 0;
        }
    }
    public static boolean remove(int userID, Channel channel) {
        if (mapToChannel.containsKey(userID)) {
            mapToChannel.remove(userID);
            mapToUser.remove(channel);
            return true;
        } else {
            return false;
        }
    }

}
