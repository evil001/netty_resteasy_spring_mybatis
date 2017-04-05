package com.qbao.im.api.server;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import org.jboss.resteasy.core.SynchronousDispatcher;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.plugins.server.netty.RequestDispatcher;
import org.jboss.resteasy.plugins.server.netty.RequestHandler;
import org.jboss.resteasy.plugins.server.netty.RestEasyHttpRequestDecoder;
import org.jboss.resteasy.plugins.server.netty.RestEasyHttpResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qbao.im.api.server.dispatcher.IntroRequestDispatcher;
import com.qbao.im.api.server.handler.NettyServerHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslHandler;

public class NettyServer extends NettyJaxrsServer {

	private Logger logger = LoggerFactory.getLogger(NettyServer.class);

	private EventLoopGroup eventLoopGroup;

	private EventLoopGroup eventExecutor;

	/**
	 * 根据cpu核数设置优化nio线程数量
	 */
	private int ioWorkerCount = Runtime.getRuntime().availableProcessors() * 2;

	private int executorThreadCount = 50;

	private SSLContext sslContext;

	private int maxRequestSize = 1024 * 1024 * 10;

	private int backlog = 128;

	private int nettyPort;

	public NettyServer(int nettyPort) {
		this.nettyPort = nettyPort;
	}

	public NettyServer() {
	}

	@Override
	public void setSSLContext(SSLContext sslContext) {
		this.sslContext = sslContext;
	}

	@Override
	public void setIoWorkerCount(int ioWorkerCount) {
		this.ioWorkerCount = ioWorkerCount;
	}

	@Override
	public void setExecutorThreadCount(int executorThreadCount) {
		this.executorThreadCount = executorThreadCount;
	}

	@Override
	public void setMaxRequestSize(int maxRequestSize) {
		this.maxRequestSize = maxRequestSize;
	}

	@Override
	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}

	@Override
	public void start() {
		eventLoopGroup = new NioEventLoopGroup(ioWorkerCount);
		eventExecutor = new NioEventLoopGroup(executorThreadCount);
		deployment.start();
		final RequestDispatcher dispatcher = new IntroRequestDispatcher(
				(SynchronousDispatcher) deployment.getDispatcher(), deployment.getProviderFactory(), domain);
		// Configure the server.
		if (sslContext == null) {
			bootstrap.group(eventExecutor).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new HttpRequestDecoder());
							ch.pipeline().addLast(new HttpObjectAggregator(maxRequestSize));
							ch.pipeline().addLast(new HttpResponseEncoder());
							ch.pipeline().addLast(new RestEasyHttpRequestDecoder(dispatcher.getDispatcher(), root,
									RestEasyHttpRequestDecoder.Protocol.HTTP));
							ch.pipeline().addLast(new NettyServerHandler());
							ch.pipeline().addLast(new RestEasyHttpResponseEncoder());
							ch.pipeline().addLast(eventExecutor, new RequestHandler(dispatcher));
						}
					}).option(ChannelOption.SO_BACKLOG, backlog).childOption(ChannelOption.SO_KEEPALIVE, true);
		} else {
			final SSLEngine engine = sslContext.createSSLEngine();
			engine.setUseClientMode(false);
			bootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addFirst(new SslHandler(engine));
							ch.pipeline().addLast(new HttpRequestDecoder());
							ch.pipeline().addLast(new HttpObjectAggregator(maxRequestSize));
							ch.pipeline().addLast(new HttpResponseEncoder());
							ch.pipeline().addLast(new RestEasyHttpRequestDecoder(dispatcher.getDispatcher(), root,
									RestEasyHttpRequestDecoder.Protocol.HTTPS));
							ch.pipeline().addLast(new NettyServerHandler());
							ch.pipeline().addLast(new RestEasyHttpResponseEncoder());
							ch.pipeline().addLast(eventExecutor, new RequestHandler(dispatcher));
						}
					}).option(ChannelOption.SO_BACKLOG, backlog).childOption(ChannelOption.SO_KEEPALIVE, true);
		}
		logger.info("nettyport:{}", nettyPort);
		bootstrap.bind(nettyPort).syncUninterruptibly();
	}

}
