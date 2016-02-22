package com.goldmine.webstat.computation.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataDigester {

	private static final Logger logger = LogManager.getLogger(DataDigester.class);

	private DataDigester() {
		// empty
	}

	private static MessageDigest getMessageDigest() {
		try {
			return MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-1 Algorithm is not available", e);
		}
	}

	/**
	 * 将字节数组转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuilder sbDes = new StringBuilder();
		String tmp = null;
		for (int i = 0; i < b.length; i++) {
			tmp = (Integer.toHexString(b[i] & 0xFF));
			if (tmp.length() == 1) {
				sbDes.append("0");
			}
			sbDes.append(tmp);
		}
		return sbDes.toString();
	}

	public static String digest(String strSrc) {
		try {
			MessageDigest digest = getMessageDigest();
			byte[] bt = strSrc.getBytes("utf-8");
			digest.update(bt);
			return byte2hex(digest.digest());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString(), e);
			return null;
		}
	}

}
