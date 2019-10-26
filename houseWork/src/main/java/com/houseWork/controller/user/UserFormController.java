package com.houseWork.controller.user;

import com.houseWork.entity.user.MessagePutParam;
import com.houseWork.entity.user.UserFormParam;
import com.houseWork.service.user.UserFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "消息提醒", description = "消息提醒")
@RestController
@RequestMapping("/userForm")
public class UserFormController {
    @Autowired
    UserFormService userFormService;
    /**
     * 发送模板消息
     */
    @ApiOperation(value = "消息发送", notes = "消息发送")
    @PostMapping("/message")
    public ResponseEntity messagePut(@RequestBody MessagePutParam param) {
        return userFormService.messageput(param);
    }
    /**
     *  存入formId
     * @param param
     * @return
     */
    @ApiOperation(value = "保存formId", notes = "保存formId")
    @PostMapping("/insertForm")
    public ResponseEntity insertFormId(@RequestBody UserFormParam param) {
        userFormService.insertFormId(param);
        return  new ResponseEntity(HttpStatus.OK);
    }
}
