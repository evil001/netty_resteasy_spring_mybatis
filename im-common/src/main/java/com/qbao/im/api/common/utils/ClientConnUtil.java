package com.qbao.im.api.common.utils;

import com.qbao.im.api.common.msg.ClientMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by tangxiaojun on 2017/3/27.
 */
public class ClientConnUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClientConnUtil.class);

    public static ConcurrentMap<Long,ClientConnection> allClient = new ConcurrentHashMap<>();

    public static ClientConnection getClientConnection(ChannelHandlerContext ctx){
        Long netId = ctx.attr(ClientConnection.NETID).get();

        ClientConnection conn = allClient.get(netId);
        if(conn != null){
            return conn;
        }else {
            logger.error("ClientConnection not found in allClient,netId:{}",netId);
        }
        return null;
    }

    public static ClientConnection getClientConnection(Long netId){
        ClientConnection conn = allClient.get(netId);
        if(conn != null){
            return conn;
        }else {
            logger.error("ClientConnection not found in allClient,netId:{}",netId);
        }
        return null;
    }

    public static void addClientConnection(ChannelHandlerContext ctx){
        ClientConnection conn = new ClientConnection(ctx);

        if(ClientConnUtil.allClient.putIfAbsent(conn.getNetId(),conn) != null){
            logger.error("重复连接踢掉原来链接");
        }

//        ctx.writeAndFlush(PacketMessage.sendMessageBody());
    }

    public static void removeClientConnection(ChannelHandlerContext ctx){
        ClientConnection conn = getClientConnection(ctx);

        Long netId = conn.getNetId();
        String userId = conn.getUserId();
        if(ClientConnUtil.allClient.remove(netId) == null){
            logger.error("NetId:{} is not exist in allClent");
        }

        logger.info("client disconnected ,netId:{},userId:{}",netId,userId);
    }
}
