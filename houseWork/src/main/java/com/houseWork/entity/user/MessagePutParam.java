package com.houseWork.entity.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(value = "信息提醒实体")
@Data
public class MessagePutParam {
    @ApiModelProperty(value = "openid")
    private String openId;
    private String template_id;
    private String page;
    private String form_id;
    private String data;
    private String emphasis_keyword;


}
