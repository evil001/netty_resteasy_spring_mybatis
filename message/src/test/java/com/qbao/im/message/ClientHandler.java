package com.qbao.im.message;

import com.google.flatbuffers.FlatBufferBuilder;
import com.qbao.im.api.common.flatbuf.Message;
import com.qbao.im.api.common.utils.PacketMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by tangxiaojun on 2017/3/27.
 */
public class ClientHandler extends SimpleChannelInboundHandler<Message> {

    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    public static ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws IOException{
        this.ctx = ctx;
        logger.info("成功链接以后向服务端发送消息");
//        sendMessage();
    }

    void sendMessage(){
        String msg = "客户端发送一条消息内容，================";
        Long netId = 1l;
        String userId = "10001";
        String self = "10001";
        String dest = "10002";
        String platform = "android";
        String version = "1.0";
        FlatBufferBuilder fbb = PacketMessage.sendMessageBody(userId,netId,self,dest,msg,platform,version);
        ctx.writeAndFlush(fbb.dataBuffer());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        sendMessage();
    }
}
