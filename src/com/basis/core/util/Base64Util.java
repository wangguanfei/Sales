package com.basis.core.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
/**
 * 字符串--64位进制 编码
 * @author wxliu
 *
 */
public class Base64Util {
	// 把普通字符编码成64bit
	public static String StrToBase64(String s)
			throws UnsupportedEncodingException {
		if (s == null)
			return null;
		try {
			byte[] b = Base64.encodeBase64(s.getBytes("UTF-8"));
			return new String(b, "UTF-8");
		} catch (Exception e) {
			return null;
		}
	}

	// 把64bit编码转化成普通字符串
	public static String Base64ToStr(String s) {
		if (s == null)
			return null;
		try {
			byte[] b = Base64.decodeBase64(s.getBytes("UTF-8"));
			return new String(b, "UTF-8");
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(Base64ToStr("5oiR54ix5YyX5Lqs5aSp5a6J6Zeo"));
		System.out.println(StrToBase64("我爱北京天安门"));
		System.out.println(Base32Util.StrToBase32("我爱北京天安门"));
		System.out.println(Base32Util.Base32ToStr("42ejdz4iwhsyzf7exkwoljfj4wxit2mxva"));
	}
}
