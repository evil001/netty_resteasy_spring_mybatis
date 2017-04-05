package com.qbao.im.api.server.handler;

import java.io.IOException;

import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<NettyHttpRequest> {

	private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, NettyHttpRequest request) throws Exception {
		request.getResponse().getOutputHeaders().add("Access-Control-Allow-Origin", "*");
		request.getResponse().getOutputHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		request.getResponse().getOutputHeaders().add("Access-Control-Allow-Headers",
				"X-Requested-With, Content-Type, Content-Length");

		ctx.fireChannelRead(request);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws IOException {
		logger.info("==========================连接接入=================");
	}

}
