package com.qbao.im.api.server.container;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.ResteasyDeployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.qbao.im.api.server.NettyServer;

@Component
public class NettyContainer {

	private static final Logger logger = LoggerFactory.getLogger(NettyContainer.class);

	private NettyServer server;

	@Autowired
	private ApplicationContext ac;

	@Value("${im.server.asyncJobServiceEnabled}")
	private String asyncJobServiceEnabled;

	@Value("${im.server.asyncJobServiceMaxJobResults}")
	private String asyncJobServiceMaxJobResults;

	@Value("${im.server.asyncJobServiceMaxWait}")
	private String asyncJobServiceMaxWait;

	@Value("${im.server.asyncJobServiceThreadPoolSize}")
	private String asyncJobServiceThreadPoolSize;

	@Value("${im.server.port}")
	private Integer port;

	public NettyContainer() {

	}

	@PostConstruct
	private void initServer() {
		logger.info("init server config info");
		// 获取resteasy对象
		ResteasyDeployment deployment = new ResteasyDeployment();

		// int nettyPort = Integer.valueOf(port);
		int nettyPort = port;
		logger.info("nettyPort:{}", nettyPort);
		// 设置是否支持异步
		deployment.setAsyncJobServiceEnabled(Boolean.valueOf(asyncJobServiceEnabled));
		// 设置最大异步工作线程
		deployment.setAsyncJobServiceMaxJobResults(Integer.valueOf(asyncJobServiceMaxJobResults));
		// 设置最大等待时间
		deployment.setAsyncJobServiceMaxWait(Integer.valueOf(asyncJobServiceMaxWait));
		// 设置线程池大小
		deployment.setAsyncJobServiceThreadPoolSize(Integer.valueOf(asyncJobServiceThreadPoolSize));

		Collection<Object> providers = ac.getBeansWithAnnotation(Provider.class).values();
		Collection<Object> controllers = ac.getBeansWithAnnotation(Controller.class).values();
		// 添加providers
		if (providers != null) {
			deployment.getProviders().addAll(providers);
		}
		// 将所有的注解controller加进resources
		deployment.getResources().addAll(controllers);

		server = new NettyServer(nettyPort);
		server.setDeployment(deployment);
		server.setPort(nettyPort);
		server.setRootResourcePath("");
		server.setSecurityDomain(null);
		server.start();
	}

	@PreDestroy
	public void cleanUp() {
		server.stop();
	}
}
