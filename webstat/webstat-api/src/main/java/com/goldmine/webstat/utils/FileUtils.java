package com.goldmine.webstat.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

/**
 * 提供一个便捷方式获取属性值
 * 
 * @author zhaoxuanzhang
 * 
 */
@Component
public class FileUtils {

	public File getFile(String name) {
		URL url = FileUtils.class.getResource("/" + name);
		return new File(url.getFile());
	}

	public String readFileAsString(String filename) {
		File file = this.getFile(filename);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return IOUtils.toString(fis);
		} catch (IOException e) {
			throw new RuntimeException("error while loading " + filename, e);
		} finally {
			IOUtils.closeQuietly(fis);
		}
	}

	public byte[] readFileAsBytes(String filename) {
		File file = this.getFile(filename);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return IOUtils.toByteArray(fis);
		} catch (IOException e) {
			throw new RuntimeException("error while loading " + filename, e);
		} finally {
			IOUtils.closeQuietly(fis);
		}
	}
}
