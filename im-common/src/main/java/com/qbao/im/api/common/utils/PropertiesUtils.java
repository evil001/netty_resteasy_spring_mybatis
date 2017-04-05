package com.qbao.im.api.common.utils;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tangxiaojun on 2017/3/23.
 */
public class PropertiesUtils {

	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

	public static <T> String getProperty(Class<T> cla, String file, String key) {
		InputStream is = null;
		try {
			is = cla.getClass().getResourceAsStream(file);
			Properties pro = new Properties();
			pro.load(is);
			return pro.getProperty(key);
		} catch (Exception e) {
			logger.error("read file error");
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.load(PropertiesUtils.class.getResourceAsStream("app.properties"));
		System.out.print(properties.getProperty("spring.active.profile"));
	}
}
