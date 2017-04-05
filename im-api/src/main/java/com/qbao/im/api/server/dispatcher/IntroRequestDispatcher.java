package com.qbao.im.api.server.dispatcher;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.core.SynchronousDispatcher;
import org.jboss.resteasy.plugins.server.embedded.SecurityDomain;
import org.jboss.resteasy.plugins.server.netty.RequestDispatcher;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.HttpResponse;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.spi.ResteasyUriInfo;
//import org.jboss.resteasy.core.SynchronousDispatcher;
//import org.jboss.resteasy.plugins.server.embedded.SecurityDomain;
//import org.jboss.resteasy.plugins.server.netty.RequestDispatcher;
//import org.jboss.resteasy.spi.HttpRequest;
//import org.jboss.resteasy.spi.HttpResponse;
//import org.jboss.resteasy.spi.ResteasyProviderFactory;
//import org.jboss.resteasy.spi.ResteasyUriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

public class IntroRequestDispatcher extends RequestDispatcher {

	// public class IntroRequestDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(IntroRequestDispatcher.class);

	public IntroRequestDispatcher(SynchronousDispatcher dispatcher, ResteasyProviderFactory providerFactory,
			SecurityDomain domain) {
		super(dispatcher, providerFactory, domain);
	}

	@Override
	public void service(ChannelHandlerContext ctx, HttpRequest request, HttpResponse response, boolean handleNotFound)
			throws IOException {
		InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
		logger.info("CanonicalHostName:" + socketAddress.getAddress().getCanonicalHostName());
		ResteasyUriInfo resteasyUriInfo = request.getUri();
		logger.info("Path:" + resteasyUriInfo.getPath());
		logger.info("RequestUrl:" + resteasyUriInfo.getRequestUri());
		logger.info("BaseUri:" + resteasyUriInfo.getBaseUri());
		URI uri = resteasyUriInfo.getAbsolutePath();
		logger.info("AbsolutePath:" + uri);
		logger.info("URI.path:" + uri.getPath());
		logger.info("URI.RawPath:" + uri.getRawPath());
		logger.info("URI.Fragment:" + uri.getFragment());
		logger.info("URI.SchemeSpecificPart:" + uri.getSchemeSpecificPart());
		logger.info("URI.Authority:" + uri.getAuthority());
		logger.info("URI.Query:" + uri.getQuery());
		logger.info("URI.UserInfo:" + uri.getUserInfo());
		logger.info("URI.Host:" + uri.getHost());
		logger.info("URI.Port:" + uri.getPort());
		HttpHeaders httpHeaders = request.getHttpHeaders();
		MultivaluedMap<String, String> reqHeaders = httpHeaders.getRequestHeaders();
		for (String key : reqHeaders.keySet()) {
			logger.info(key + "=" + reqHeaders.getFirst(key));
		}
		super.service(ctx, request, response, handleNotFound);
	}
}
