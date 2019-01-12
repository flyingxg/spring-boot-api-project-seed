package com.company.project.service;

import com.xiaoleilu.hutool.http.HttpUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Date;

public class QuestionUtils {
	private static MessageDigest md5 = null;
	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	static final String app_secret = "f92d6cf6c1794b2c819e379b47e1585c";
	static final String APP_KEY = "23339ae8f2e045a0912eb45b19da24d2";


	public static String getURLEncoderString(String str) {
		String result = "";
		if (null == str) {
			return "";
		}
		try {
			result = java.net.URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getIntroduction(String problem) {
		String timestamp = new Date().getTime() + "";
		String string = app_secret + "api=nliappkey=" + APP_KEY + "timestamp=" + timestamp + app_secret;
		String sign = getMd5(string);
		System.out.println(timestamp);
		String rq = "{\"data\":{\"input_type\":1,\"text\":\"" + problem + "\"},\"data_type\":\"stt\"}";
		String url = "http://cn.olami.ai/cloudservice/api?appkey=23339ae8f2e045a0912eb45b19da24d2&api=nli&timestamp="
				+ timestamp + "&sign=" + sign +"&rq=" +  getURLEncoderString(rq);
		// System.out.println(HttpUtil.postData(url,null));
		return (HttpUtil.post(url,rq));
	}

	public static String getMd5(String string) {
		try {
			byte[] bs = md5.digest(string.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder(40);
			for (byte x : bs) {
				if ((x & 0xff) >> 4 == 0) {
					sb.append("0").append(Integer.toHexString(x & 0xff));
				} else {
					sb.append(Integer.toHexString(x & 0xff));
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
