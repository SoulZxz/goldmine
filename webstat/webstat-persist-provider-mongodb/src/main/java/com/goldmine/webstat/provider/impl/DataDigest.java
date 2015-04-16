package com.goldmine.webstat.provider.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class DataDigest {

	private static final Log log = LogFactory.getLog(DataDigest.class);

	private static final MessageDigest digest = getMessageDigest();

	private DataDigest() {
	}

	private static MessageDigest getMessageDigest() {
		try {
			return MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-512 Algorithm is not available", e);
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
			byte[] bt = strSrc.getBytes("utf-8");
			digest.update(bt);
			return byte2hex(digest.digest());
		} catch (UnsupportedEncodingException e) {
			log.error(e.toString(), e);
			return null;
		}
	}

}
