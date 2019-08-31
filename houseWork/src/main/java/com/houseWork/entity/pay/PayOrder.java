package com.houseWork.entity.pay;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.houseWork.dao.user.UserDao;
import com.houseWork.entity.user.User;

import lombok.Data;

@Data
public class PayOrder {
	private String id;
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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreated;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;
	private User clearner;
	private User employer;
}
