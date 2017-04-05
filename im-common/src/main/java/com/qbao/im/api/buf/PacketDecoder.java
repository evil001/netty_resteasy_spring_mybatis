package com.qbao.im.api.buf;

import com.qbao.im.api.common.flatbuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by tangxiaojun on 2017/3/27.
 */
public class PacketDecoder extends MessageToMessageDecoder<Message> {
    @Override
    protected void decode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {

    }
}
