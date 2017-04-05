package com.qbao.im.message;

import com.qbao.im.api.buf.PacketDecoder;
import com.qbao.im.api.buf.PacketEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tangxiaojun on 2017/3/27.
 */
public class ClientTest {

    private static final Logger logger = LoggerFactory.getLogger(ClientTest.class);

    private static final String HOST = System.getProperty("host","127.0.0.1");

    private static final int PORT = Integer.parseInt(System.getProperty("port","8080"));

    public static void main(String[] args) throws InterruptedException {
        beginPressTest();
    }

    public static void beginPressTest() throws InterruptedException{
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY,true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("MessageDecoder",new PacketDecoder());
                pipeline.addLast("MessageEncoder",new PacketEncoder());
                pipeline.addLast(new ClientHandler());
            }
        });

        startConn(bootstrap,1);
    }

    private static void startConn(Bootstrap bootstrap,final int index){
        bootstrap.connect(HOST,PORT).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()){
                    logger.info("启动成功");
                }else {
                    logger.error("启动失败");
                }
            }
        });
    }
}
