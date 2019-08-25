package com.houseWork.entity.pay;

import java.util.Date;

import lombok.Data;

@Data
public class PayOrder {
	private int id;
	private String orderId;
	private String goodsId;
	private Double payPrice;
	private Double totalPrice;
	private Integer employerId;
	private Integer clearnerId;
	private String employerName;
	private String employerPhone;
	private String address;
	private String content;
	private String picUrl;
	private String videoUrl;
	private Double area;
	private Integer level;
	private Integer longTime;
	private Integer chargingType;
	private Integer goodsType;
	
	private Integer logistics;
	private Integer orderState;
	private Integer delflag;
	
	private Date startTime;
	private Date endTime;
	private Date gmtCreated;
	private Date gmtModified;
	
}
