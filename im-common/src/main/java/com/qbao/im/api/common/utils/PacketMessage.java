package com.qbao.im.api.common.utils;

import com.google.flatbuffers.FlatBufferBuilder;
import com.qbao.im.api.common.flatbuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by tangxiaojun on 2017/3/27.
 */
public class PacketMessage {

    private static final Logger logger = LoggerFactory.getLogger(PacketMessage.class);

    public static FlatBufferBuilder sendMessageBody(String userId,Long netId,String self,String dest,String contet,String platform,String version){
        FlatBufferBuilder fbb = new FlatBufferBuilder(1);
        int intUserId = fbb.createString(userId);
        int intNetId = fbb.createString(netId.toString());
        int intSelf = fbb.createString(self);
        int intDest = fbb.createString(dest);
        int intCon = fbb.createString(contet);
        int intPlatform = fbb.createString(platform);
        int intaVersion = fbb.createString(version);
        Message.startMessage(fbb);
        Message.addUserId(fbb,intUserId);
        Message.addNetId(fbb,intNetId);
        Message.addSelf(fbb,intSelf);
        Message.addDest(fbb,intDest);
        Message.addContent(fbb,intCon);
        Message.addPlatform(fbb,intPlatform);
        Message.addVersion(fbb,intaVersion);
        fbb.finish(Message.endMessage(fbb));
        logger.info("发送消息体封装,Message:{}",Message.getRootAsMessage(fbb.dataBuffer()));
        return fbb;
    }

    public static Message getMessageBody(FlatBufferBuilder fbb){
        return Message.getRootAsMessage(fbb.dataBuffer());
    }

    public static final ByteBuf str2ByteBuf(String str){
        ByteBuf byteBuf = Unpooled.buffer(8);
        byteBuf.writeBytes(str.getBytes());
        return byteBuf;
    }

    public static final String byteBuf2Str(ByteBuf buf) throws UnsupportedEncodingException {

        byte[] bytes = new byte[buf.readableBytes()];
//        byte[] lenBytes = new byte[bytes.length-1];
//        System.arraycopy(bytes,1,lenBytes,0,bytes.length-2);
        buf.readBytes(bytes);
        String content = new String(bytes,"UTF-8");
        content = content.substring(content.indexOf("{"),content.lastIndexOf("}")+1);
        return content;
    }
}
