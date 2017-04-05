package com.qbao.im.api.common.utils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tangxiaojun on 2017/3/27.
 */
public class ClientConnection {

    Logger logger = LoggerFactory.getLogger(ClientConnection.class);

    public static AttributeKey<Long> NETID = AttributeKey.valueOf("netId");

    private String userId;

    private Long netId;

    private ChannelHandlerContext ctx;

    public ClientConnection(ChannelHandlerContext ctx) {
        Long id = 1l;
        netId = id;
        this.ctx = ctx;
        this.ctx.attr(ClientConnection.NETID).set(netId);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNetId(Long netId) {
        this.netId = netId;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public String getUserId() {
        return userId;
    }

    public Long getNetId() {
        return netId;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }
}
