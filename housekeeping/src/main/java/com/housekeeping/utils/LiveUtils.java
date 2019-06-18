package com.housekeeping.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class LiveUtils {
	
		private static final String KEY="8c22dd13d190d1b530e9898e18b3c492";
		
		private static final String BIZ_ID="39316";
	
	    public static void main(String[] args) {
	     //   System.out.println(getSafeUrl("live.tiny.big.com","txrtmp", "11212122", 1469762325L));
	    }
	
	    private static final char[] DIGITS_LOWER =
	        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	    //播放地址后缀名
	    private static final String[] LIVE_FORMAT= {"",".fly",".m3u8"};
	    //传输方式 与后缀名一一对应
	    private static final String[] TRANSPORT= {"rtmp://","http://","http://"};
	    /*
	     * KEY+ stream_id + txTime
	     */
	    /**
	       * 得到推流地址
	     * @param domain
	     * @param streamId
	     * @return
	     */
	    public static String getPushUrl(String domain,String key, String streamId, long txTime) {
	        String input = new StringBuilder().
	                append(key).
	                append(streamId).
	                append(Long.toHexString(txTime).toUpperCase()).toString();
	
	        String txSecret = null;
	        try {
	            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	            txSecret  = byteArrayToHexString(
	                    messageDigest.digest(input.getBytes("UTF-8")));
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	
	        return txSecret == null ? "" :
	            new StringBuilder().
	                append("rtmp://").
	                append(domain).
	                append("/live/").
	                append(streamId).
	                append("?bizid="+BIZ_ID).
	                append("&txSecret=").
	                append(txSecret).
	                append("&").
	                append("txTime=").
	                append(Long.toHexString(txTime).toUpperCase()).
	                toString();
	    }
	    /**
	       * 得到播放地址
	     * @param domain
	     * @param streamId
	     * @return
	     */
	    public static List<String> getLiveUrl(String domain,String streamId){
	    	String liveUrlFront=new StringBuilder().
     
            append(domain).
            append("/live/").
            append(streamId).
            toString();
	    	List<String> liveUrlList= new ArrayList<>();
	    	for(int i=0;i<LIVE_FORMAT.length;i++) {
	    		liveUrlList.add(TRANSPORT[i]+liveUrlFront+LIVE_FORMAT[i]);	
	    	}
			return liveUrlList;
	    		  
	    }
	    private static String byteArrayToHexString(byte[] data) {
	        char[] out = new char[data.length << 1];
	
	        for (int i = 0, j = 0; i < data.length; i++) {
	            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
	            out[j++] = DIGITS_LOWER[0x0F & data[i]];
	        }
	        return new String(out);
	    }
	   
	
}
