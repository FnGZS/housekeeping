package com.houseWork.entity.pay;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.user.User;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * @author zzc
 */
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
	@Min(value = 360,message = "不得小于三个小时")
	private Integer longTime;
	private Integer chargingType;
	private Integer goodsType;
	private Double unitPrice;
	private Integer logistics;
	private Integer orderState;
	private Integer delFlag;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreated;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;
	private Cleaner cleaner;
	private User employer;
}
