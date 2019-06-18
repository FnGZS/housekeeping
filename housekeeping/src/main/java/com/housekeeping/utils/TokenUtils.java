package com.housekeeping.utils;

import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class TokenUtils {
	private static final String Open_AES_KEY = "M03trp$J7yo+Eu8S";

	public static String creatAesStr(Long openId) {
		if (openId == null) {
			return "";
		}
		UUID uuid = UUID.randomUUID();
		String data = uuid.toString() + "|" + openId;
		String key = new String(Base64.encodeBase64("M03trp$J7yo+Eu8S".getBytes()));
		return AesUtils.encrypt(data, key);
	}

	public static Long getIdFromAesStr(String str) throws Exception {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String key = new String(Base64.encodeBase64("M03trp$J7yo+Eu8S".getBytes()));

		String originStr = AesUtils.decrypt(str, key);
		String id = originStr.split("\\|")[1];
		if (StringUtils.isBlank(id)) {
			return null;
		}
		return Long.valueOf(Long.parseLong(id));
	}
}