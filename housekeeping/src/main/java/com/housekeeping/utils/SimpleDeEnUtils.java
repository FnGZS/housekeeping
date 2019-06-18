package com.housekeeping.utils;

import java.nio.charset.Charset;

/**
 * @Type SimpleDeEnUtils.java
 * @Desc 
 * @author luo
 * @date 2017年7月14日 下午1:20:34
 * @Version V1.0
 */
public class SimpleDeEnUtils {

	private static final String key0 = "OI()*&<MNCXZ";
	private static final Charset charset = Charset.forName("UTF-8");
	private static byte[] keyBytes = key0.getBytes(charset);
	
	public static void main(String[] args) {
		System.out.println(decodeToLong("MLLE"));
		System.out.println(encodeFromLong(3070L));
	}
	
	public static String encodeFromLong(Long enc){
		return encodeFromString(enc + "");
	}
	
	public static Long decodeToLong(String dec){
		try {
			return Long.parseLong(decodeToString(dec));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String encodeFromString(String enc){
		byte[] b = enc.getBytes(charset);
		for(int i=0,size=b.length;i<size;i++){
			for(byte keyBytes0:keyBytes){
				b[i] = (byte) (b[i]^keyBytes0);
			}
		}
		return new String(b);
	}
	
	public static String decodeToString(String dec){
		byte[] e = dec.getBytes(charset);
		byte[] dee = e;
		for(int i=0,size=e.length;i<size;i++){
			for(byte keyBytes0:keyBytes){
				e[i] = (byte) (dee[i]^keyBytes0);
			}
		}
		return new String(e);
	}
}
