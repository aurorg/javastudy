package server.serverhandler;

import cn.hutool.core.date.DateTime;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import message.FileResponseMessage;
import message.FriendChatmsg;
import message.GroupChatMessage;

import java.io.File;
@Slf4j

public class ReceiverFileHandler extends ChannelInboundHandlerAdapter {

    private static FriendChatmsg friendMsg;
    private static GroupChatMessage groupMsg;
    private String path;
    private static int start=0;
    private static int size,rate,length;
    private static boolean receive;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DateTime dateTime=new DateTime(System.currentTimeMillis());
        if(msg instanceof FriendChatmsg){
            log.info("msg instanceof FriendChatRequestMessage ");

            if(((FriendChatmsg) msg).getFile()==null){//不是发送文件的消息
                super.channelRead(ctx, msg);
                return;
            }
            byte[] bytes=((FriendChatmsg) msg).getFile();
            if(bytes.length!=1||bytes[0]!=1){
                friendMsg = (FriendChatmsg) msg;
                size=friendMsg.getFileSize();
                if(friendMsg.getPath()!=null){
                    path= friendMsg.getPath();
                }else {
                    path= System.getProperty("user.dir") + "/chatroom/server/story/" + friendMsg.getFileName() + dateTime;
                }
                friendMsg.setPath(path);
            }

            File file=new File(path);
            log.info("path = {} ,file.exists = {}, file.length = {} ,fileLength = {}",path,file.exists(),file.length(),size);
            if(file.exists()&&file.length()==size){
                //接收完毕
                log.info("接收完毕");
                super.channelRead(ctx, friendMsg);
            }else{
                //开始接收
                ctx.writeAndFlush(new FileResponseMessage(0,path));
            }
        }

        //群的
//        else if(msg instanceof GroupChatMessage){
//            //log.info("msg instanceof GroupChatRequestMessage");
//
//            if(((GroupChatMessage) msg).getFile()==null){//不是发送文件的消息
//                super.channelRead(ctx, msg);
//                return;
//            }
//            byte[] bytes=((GroupChatMessage)msg).getFile();
//            if(bytes.length!=1||bytes[0]!=1){
//                groupMsg = (GroupChatMessage) msg;
//                size=groupMsg.getFileSize();
//                if(groupMsg.getPath()!=null){
//                    path= groupMsg.getPath();
//                }else {
//                    path= System.getProperty("user.dir") + "/chatroom/server/story/" + groupMsg.getFileName() + dateTime;
//                }
//                groupMsg.setPath(path);
//            }
//
//            File file=new File(path);
//            log.info("path = {} ,file.exists = {}, file.length = {} ,fileLength = {}",path,file.exists(),file.length(),size);
//            if(file.exists()&&file.length()==size){
//                //接收完毕
//                log.info("接收完毕");
//                super.channelRead(ctx, groupMsg);
//            }else{
//                //开始接收
//                ctx.writeAndFlush(new FileResponseMessage(0,path));
//            }
//        }
        else{
            super.channelRead(ctx, msg);
        }
    }

}
