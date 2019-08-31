package com.houseWork.controller.user;

import com.houseWork.entity.Test.Test;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.service.test.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
@Api(tags = "试卷接口", description = "试卷接口")
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/insert")
    @ApiOperation(value = "添加试卷",notes = "添加试卷")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "title", value = "title", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "content", value = "content", dataType = "String")
    })
    public ResponseEntity insertTest(@RequestParam String title,
                                     @RequestParam String content){
        return new ResponseEntity(ResponseResult.successResponse(testService.insert(Test.builder()
                .title(title)
                .testContent(content)
                .createTime(new Date())
                .build())), HttpStatus.OK);
    }

}
