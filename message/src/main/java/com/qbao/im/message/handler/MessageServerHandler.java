package com.qbao.im.message.handler;

import com.qbao.im.api.common.flatbuf.Message;
import com.qbao.im.api.common.utils.ClientConnUtil;
import com.qbao.im.api.common.utils.ClientConnection;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tangxiaojun on 2017/3/27.
 */
public class MessageServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    Logger logger = LoggerFactory.getLogger(MessageServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        ClientConnUtil.addClientConnection(ctx);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        ClientConnection conn = ClientConnUtil.getClientConnection(ctx);
//        logger.info("获取客户端传来消息,netId:"+msg.userId());
//        logger.info("获取客户端传来消息,netId:"+msg.platform());
//        logger.info("获取客户端传来消息,netId:"+msg.netId());
//        logger.info("获取客户端传来消息,netId:"+msg.self());
//        logger.info("获取客户端传来消息,netId:"+msg.dest());
//        logger.info("获取客户端传来消息,netId:"+msg.content());
//        logger.info("获取客户端传来消息,netId:"+msg.version());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        ClientConnUtil.removeClientConnection(ctx);
    }
}
