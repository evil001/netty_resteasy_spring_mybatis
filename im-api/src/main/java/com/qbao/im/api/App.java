package com.qbao.im.api;

import java.io.IOException;
import java.util.Arrays;

import javax.naming.ConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import com.qbao.im.api.common.utils.PropertiesUtils;

/**
 * App!
 *
 */
public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	private static final String CONF_PROPERTIES = "/app.properties";

	public static void main(String[] args) throws ConfigurationException, IOException, ClassNotFoundException {

		String currEnv = null;
		String active = PropertiesUtils.getProperty(App.class, CONF_PROPERTIES, "spring.active.profile");
		if (active.equals("dev")) {
			currEnv = "classpath:/dev/applicationContext_dev.xml";
		} else if (active.equals("prod")) {
			currEnv = "classpath:/prod/applicationContext.xml";
		}

		ApplicationContext context = new ClassPathXmlApplicationContext(currEnv);

		Assert.notNull(context);
		Assert.notNull(context.getBeanDefinitionNames());
		String[] beanNames = context.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			logger.info("================" + beanName);
		}
		logger.info("server start ");
	}
}
