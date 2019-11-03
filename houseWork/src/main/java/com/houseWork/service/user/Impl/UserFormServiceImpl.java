package com.houseWork.service.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.houseWork.dao.user.UserFormDao;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.entity.user.MessagePutParam;
import com.houseWork.entity.user.UserFormParam;
import com.houseWork.entity.weixin.MessageInfo;
import com.houseWork.entity.weixin.UserFormDO;
import com.houseWork.service.user.UserFormService;
import com.houseWork.service.weixin.WeixinAppService;
import com.houseWork.service.weixin.domin.WeixinGeneralResult;
import com.houseWork.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class UserFormServiceImpl implements UserFormService {
    @Autowired
    WeixinAppService weixinAppService;
    @Autowired
    UserFormDao userFormDao;
    @Override
    public ResponseEntity messageput(MessagePutParam param) {

        UserFormDO formDO = userFormDao.getFormId(param.getOpenId());
        if(formDO==null) {
            return new ResponseEntity(ResponseResult.successResponse("发送失败"), HttpStatus.FAILED_DEPENDENCY);
        }
        MessageInfo info = new MessageInfo();
        info.setTouser(formDO.getOpenId());
        info.setEmphasis_keyword(param.getEmphasis_keyword());
        info.setForm_id(formDO.getFormId());
        info.setPage(param.getPage());
        Map<String, Object> map = JsonUtils.getMap4Json(param.getData());
        info.setTemplate_id(param.getTemplate_id());
        info.setData(map);
        WeixinGeneralResult response = WeixinAppService.messagePut(info);
        log.info(JSONObject.toJSONString(response));;
        if(response.getCode().code!=200) {
            return new ResponseEntity(ResponseResult.successResponse(response.getMessage()), HttpStatus.FAILED_DEPENDENCY);
        }
        userFormDao.deleteFormId(formDO.getId());
        return new ResponseEntity(ResponseResult.successResponse("发送成功"), HttpStatus.OK);
    }
    @Override
    public void insertFormId(UserFormParam param) {

        UserFormDO formDO = new UserFormDO();
        formDO.setFormId(param.getFormId());
        formDO.setOpenId(param.getOpenId());
        formDO.setUserId(param.getUserId());
        userFormDao.insertFormId(formDO);

    }

}
