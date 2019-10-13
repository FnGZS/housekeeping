package com.houseWork.entity.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(description="根据条件获取系统订单列表信息")
@Data
public class SearchPayOrderParam {
	@ApiModelProperty("保洁人员id")
	private Integer clearnerId;
	@ApiModelProperty("业主openid")
	private String employerId;
	@ApiModelProperty("0,普通清洁，1开荒，不传为全部，下同")
	private Integer goodsWay;
	@ApiModelProperty("如果为开荒选填 1开荒定金，2开荒尾款.")
	private Integer goodsType;
	@ApiModelProperty("计费方式(0,面积,1平方)")
	private Integer chargingType;
	@ApiModelProperty("订单状态0.待付款1.付全款2.付定金3.付尾款4.申请退款，5已退款")
	private Integer orderState;
	@ApiModelProperty("服务状态：0.未清洁，1已清洁")
	private Integer logistics;
}
