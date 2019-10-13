package com.houseWork.entity.pay;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * @author zzc
 */
@Data
@ApiModel(description = "订单实体")
public class PayOrder {
	@ApiModelProperty("订单编号")
	private String id;
	@ApiModelProperty("商品编号")
	private String goodsId;
	@ApiModelProperty("付款金额")
	private Double payPrice;
	@ApiModelProperty("总价")
	private Double totalPrice;
	@ApiModelProperty("业主openid")
	private String employerId;
	@ApiModelProperty("保洁员id")
	private Integer clearnerId;
	@ApiModelProperty("业主名字")
	private String employerName;
	@ApiModelProperty("业主联系方式")
	private String employerPhone;
	@ApiModelProperty("地址")
	private String address;
	@ApiModelProperty("描述")
	private String content;
	@ApiModelProperty("图片地址")
	private String picUrl;
	@ApiModelProperty("视频url")
	private String videoUrl;
	@ApiModelProperty("面积")
	private Double area;
	@ApiModelProperty("脏乱程度")
	private Integer level;
	@ApiModelProperty("时长（分钟）")
	@Min(value = 360,message = "不得小于三个小时")
	private Integer longTime;
	@ApiModelProperty("计费方式(0,面积,1小时)")
	private Integer chargingType;
	@ApiModelProperty("商品类型 0普通清洁，1开荒定金，2开荒尾款")
	private Integer goodsType;
	@ApiModelProperty("单价")
	private Double unitPrice;
	@ApiModelProperty("服务状态：0.未清洁，1已清洁")
	private Integer logistics;
	@ApiModelProperty("订单状态")
	private Integer orderState;
	@ApiModelProperty("是否删除（0删除1未删除）")
	private Integer delFlag;
	@ApiModelProperty("开始时间")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty("结束时间")
	private Date endTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty("添加时间")
	private Date gmtCreated;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty("修改时间")
	private Date gmtModified;
	@ApiModelProperty("保洁员")
	private Cleaner cleaner;
	@ApiModelProperty("雇主")
	private User employer;
}
