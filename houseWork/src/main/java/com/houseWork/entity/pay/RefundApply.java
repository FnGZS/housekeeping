package com.houseWork.entity.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
@ApiModel("申请退款实体")
public class RefundApply {
    @ApiModelProperty("编号")
    private Integer id;
    @ApiModelProperty("商品id")
    private Integer goodsId;
    @ApiModelProperty("退款类型（字典表中获取）")
    private Integer refundType;
    @ApiModelProperty("退款类型（字典表中获取）名称")
    private Integer refundTypeName;
    @ApiModelProperty("退款补充")
    private String refundContent;
    @ApiModelProperty("图片")
    private String picUrl;
    @ApiModelProperty("受理状态（0未受理，1不予退款，2退定金，3部分赔偿，4赔全款）")
    private String picVideo;
    @ApiModelProperty("视频")
    private Integer state;
    @ApiModelProperty("添加时间")
    private Date gmtCreated;
    @ApiModelProperty("修改时间")
    private Date gmtModified;
}
