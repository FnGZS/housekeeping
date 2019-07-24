package com.houseWork.service.weixin.domin;

import com.houseWork.entity.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeixinLoginResult <T>{
    private ResultCode code = ResultCode.SUCCESS;
    /**
     * 消息
     */
    private String message;

    private T dataResult;

    public WeixinLoginResult() {

    }
}
