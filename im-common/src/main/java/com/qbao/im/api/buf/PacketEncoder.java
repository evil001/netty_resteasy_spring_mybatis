package com.qbao.im.api.buf;

import com.qbao.im.api.common.flatbuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by tangxiaojun on 2017/3/27.
 */
public class PacketEncoder extends MessageToMessageEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {

    }
}
