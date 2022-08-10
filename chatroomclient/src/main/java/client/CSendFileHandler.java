package client;

import io.netty.channel.ChannelHandlerContext;
import message.FriendChatmsg;
import message.Message;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static client.ChatNettyClient.fileLength;

public class CSendFileHandler {

    private static final int MAX_LENGTH=1<<30;

    public CSendFileHandler(ChannelHandlerContext ctx, File file, Message message){
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r")){

            String sendFilePath =file.getAbsolutePath();
            String serverPath=null;
            fileLength=(int) randomAccessFile.length();

            if(fileLength>MAX_LENGTH){
                System.out.println("文件大于1G，拒绝发送！");
                return;
            }

            if(message instanceof FriendChatmsg){
               // ((FriendChatmsg)message).
            }








        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
