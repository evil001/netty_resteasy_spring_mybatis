package com.qbao.im.message.server;

import com.qbao.im.api.buf.PacketDecoder;
import com.qbao.im.api.buf.PacketEncoder;
import com.qbao.im.message.handler.MessageServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Created by tangxiaojun on 2017/3/27.
 */
public class MessageServer {

    private static final Logger logger = LoggerFactory.getLogger(MessageServer.class);

    public static void startMessageServer(int port) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(boss,worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("PacketDecoder",new PacketDecoder());
                pipeline.addLast("PacketEncoder",new PacketEncoder());
                pipeline.addLast("ClientMessageHandler",new MessageServerHandler());
            }
        });
//        bindConnectionOption(bootstrap);
        bootstrap.bind(8080).sync();
//        bootstrap.bind(new InetSocketAddress(port)).addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                if(future.isSuccess()){
//                    logger.info("服务端已经启动...");
//                }else{
//                    logger.error("服务启动失败...");
//                }
//            }
//        });
    }

    protected static void bindConnectionOption(ServerBootstrap bootstrap){
        bootstrap.option(ChannelOption.SO_BACKLOG,1024);

        bootstrap.option(ChannelOption.SO_LINGER,0);

        bootstrap.option(ChannelOption.TCP_NODELAY,true);

        bootstrap.option(ChannelOption.SO_REUSEADDR,true);

        bootstrap.option(ChannelOption.SO_KEEPALIVE,true);
    }
}
