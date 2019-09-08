package com.houseWork.entity.weixin;

import lombok.Data;

import java.util.Date;
@Data
public class Order {
	private Long id;
	private String appid;
	private String mch_id;
	private String nonce_str;
	private String sign;
	private String result_code;
	private String openid;
	private String trade_type;
	private String bank_type;
	private Integer total_fee;
	private Integer cash_fee;
	private String transaction_id;
	private String out_trade_no;
	private Date gmt_created;
	private Date gmt_modified;
	private String sin;

	
	
}
