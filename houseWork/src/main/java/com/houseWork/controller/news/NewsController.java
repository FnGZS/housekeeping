package com.houseWork.controller.news;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houseWork.entity.news.News;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.service.news.NewsService;
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
@RequestMapping("/news")
@Api(tags = "新闻接口", description = "新闻接口")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    @ApiOperation(value = "添加公告")
    public ResponseEntity add(@Validated @RequestBody News news){
        return new ResponseEntity(ResponseResult.successResponse(newsService.add(news)), HttpStatus.OK);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除公告")
    public ResponseEntity del (@RequestParam(value = "id",required = false,defaultValue = "") Integer nid){
        newsService.del(nid);
        return new ResponseEntity(ResponseResult.successResponse(), HttpStatus.OK);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改公告")
    public ResponseEntity update(@RequestParam(value = "nid",required = false,defaultValue = "")Integer nid,
                                 @RequestParam(value = "title",required = false,defaultValue = "")String title,
                                 @RequestParam(value = "content",required = false,defaultValue = "")String content,
                                 @RequestParam(value = "isRecommend",required = false,defaultValue = "")String isRecommend){
        News news = new News();
        news.setNid(nid);
        news.setTitle(title);
        news.setContent(content);
        news.setIsRecommend(isRecommend);
        return new ResponseEntity(ResponseResult.successResponse(newsService.update(news)), HttpStatus.OK);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "详情")
    public ResponseEntity detail(@RequestParam(value = "nid",required = false,defaultValue = "")Integer nid){
        return new ResponseEntity(ResponseResult.successResponse(newsService.getDetail(nid)),HttpStatus.OK);
    }

    @PostMapping("/getList")
    @ApiOperation(value = "列表")
    public ResponseEntity getList(@RequestParam(value = "title",required = false,defaultValue = "") String title,
                                  @RequestParam(value = "isRecommend",required = false,defaultValue = "")String isRecommend,
                                  @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                                  @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        Map map = new HashMap();
        map.put("title",title);
        map.put("isRecommend",isRecommend);
        PageHelper.startPage(pageNum,pageSize);
        List<News> list = newsService.getList(map);
        PageInfo<News> pageInfo = new PageInfo<News>(list);
        return new ResponseEntity(ResponseResult.successResponse(pageInfo),HttpStatus.OK);
    }
}
