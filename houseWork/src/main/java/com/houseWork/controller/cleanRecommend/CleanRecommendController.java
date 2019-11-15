package com.houseWork.controller.cleanRecommend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houseWork.entity.cleanRecommend.CleanRecommend;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.service.cleanRecommend.CleanRecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cleanRecommend")
@Api(tags = "保洁推荐接口", description = "保洁推荐接口")
public class CleanRecommendController {
    @Autowired
    private CleanRecommendService cleanRecommendService;

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public ResponseEntity add(@Validated @RequestBody CleanRecommend cleanRecommend){
        return new ResponseEntity(ResponseResult.successResponse(cleanRecommendService.add(cleanRecommend)), HttpStatus.OK);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除")
    public ResponseEntity del (@RequestParam(value = "id",required = false,defaultValue = "") Integer id){
        cleanRecommendService.del(id);
        return new ResponseEntity(ResponseResult.successResponse(), HttpStatus.OK);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public ResponseEntity update(@RequestParam(value = "id",required = false,defaultValue = "")Integer id,
                                 @RequestParam(value = "title",required = false,defaultValue = "")String title,
                                 @RequestParam(value = "content",required = false,defaultValue = "")String content,
                                 @RequestParam(value = "picture",required = false,defaultValue = "")String picture){
        CleanRecommend cleanRecommend = new CleanRecommend();
        cleanRecommend.setId(id);
        cleanRecommend.setTitle(title);
        cleanRecommend.setContent(content);
        cleanRecommend.setPicture(picture);
        return new ResponseEntity(ResponseResult.successResponse(cleanRecommendService.update(cleanRecommend)), HttpStatus.OK);
    }

    @PostMapping("/getList")
    @ApiOperation(value = "列表")
    public ResponseEntity getList(@RequestParam(value = "title",required = false,defaultValue = "") String title,
                                  @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                                  @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        Map map = new HashMap();
        map.put("title",title);
        PageHelper.startPage(pageNum,pageSize);
        List<CleanRecommend> list = cleanRecommendService.getList(map);
        PageInfo<CleanRecommend> pageInfo = new PageInfo<CleanRecommend>(list);
        return new ResponseEntity(ResponseResult.successResponse(pageInfo),HttpStatus.OK);
    }
}
