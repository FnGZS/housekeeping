package com.houseWork.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houseWork.entity.test.Test;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/selecteByMap")
    @ApiOperation(value = "查找列表", notes = "查找列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "title", value = "title", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", required = false, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页显示数量", required = false, dataType = "int")
    })
    public ResponseEntity selecteByMap(@RequestParam Integer id,
                                       @RequestParam String title,
                                       @RequestParam(defaultValue = "0") Integer pageNum,
                                       @RequestParam(defaultValue = "10") Integer pageSize){
        Map map = new HashMap();
        map.put("id",id);
        map.put("title",title);
        PageHelper.startPage(pageNum,pageSize);
        List<Test> list = testService.findByMap(map);
        PageInfo<Test> pageInfo = new PageInfo<>(list);
        return new ResponseEntity(ResponseResult.successResponse(pageInfo),HttpStatus.OK);
    }

    @PostMapping("/selectOne")
    @ApiOperation(value = "查找详情", notes = "查找详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "title", value = "title", dataType = "String")
    })
    public ResponseEntity selectOne(@RequestParam Integer id){
        return new ResponseEntity(ResponseResult.successResponse(testService.getById(id)), HttpStatus.OK);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "根据id删除",notes = "根据id删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", dataType = "String")
    })
    public ResponseEntity delete(@RequestParam Integer id){
        testService.deleteById(id);
        return new ResponseEntity(ResponseResult.successResponse(),HttpStatus.OK);
    }

}