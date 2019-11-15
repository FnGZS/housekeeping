package com.houseWork.controller.banner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houseWork.entity.banner.Banner;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.service.Banner.BannerService;
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
@RequestMapping("/banner")
@Api(tags = "轮播图接口", description = "轮播图接口")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public ResponseEntity add(@Validated @RequestBody Banner banner){
        return new ResponseEntity(ResponseResult.successResponse(bannerService.add(banner)), HttpStatus.OK);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除")
    public ResponseEntity del (@RequestParam(value = "id",required = false,defaultValue = "") Integer id){
        bannerService.del(id);
        return new ResponseEntity(ResponseResult.successResponse(), HttpStatus.OK);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public ResponseEntity update(@RequestParam(value = "id",required = false,defaultValue = "")Integer id,
                                 @RequestParam(value = "title",required = false,defaultValue = "")String title,
                                 @RequestParam(value = "content",required = false,defaultValue = "")String content,
                                 @RequestParam(value = "picture",required = false,defaultValue = "")String picture){
        Banner banner = new Banner();
        banner.setId(id);
        banner.setTitle(title);
        banner.setContent(content);
        banner.setPicture(picture);
        return new ResponseEntity(ResponseResult.successResponse(bannerService.update(banner)), HttpStatus.OK);
    }

    @PostMapping("/getList")
    @ApiOperation(value = "列表")
    public ResponseEntity getList(@RequestParam(value = "title",required = false,defaultValue = "") String title,
                                  @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                                  @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        Map map = new HashMap();
        map.put("title",title);
        PageHelper.startPage(pageNum,pageSize);
        List<Banner> list = bannerService.getList(map);
        PageInfo<Banner> pageInfo = new PageInfo<Banner>(list);
        return new ResponseEntity(ResponseResult.successResponse(pageInfo),HttpStatus.OK);
    }
}
