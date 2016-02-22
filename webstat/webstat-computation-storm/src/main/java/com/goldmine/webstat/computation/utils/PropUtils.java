package com.goldmine.webstat.computation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 提供一个便捷方式获取属性值
 * 
 * @author zhaoxuanzhang
 * 
 */
public class PropUtils {

	private static final Logger LOG = LogManager.getLogger(PropUtils.class);

	private PropUtils() {
		// empty
	}

	/**
	 * 可以在读取打在jar包里面的文件
	 * 
	 * @param propFileName
	 * @return
	 */
	public static Properties getProps(String propFileName) {
		Properties props = new Properties();
		InputStream ins = null;
		try {
			ins = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName);
			props.load(ins);
		} catch (IOException e) {
			LOG.error("error loading file " + propFileName + " " + e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(ins);
		}
		return props;
	}

}
