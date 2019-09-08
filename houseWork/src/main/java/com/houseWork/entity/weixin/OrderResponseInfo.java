package com.houseWork.entity.weixin;

import lombok.Data;

/**
 * @author zzc
 */
@Data
public class OrderResponseInfo {
	private String appId;
	private String timeStamp;
	private String nonceStr;
	private String pkg;
	private String signType;
	private String paySign;

	
}
