package com.houseWork.controller.dict;


import cn.hutool.core.lang.Dict;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.service.dict.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dict")
@Api(tags = "字典接口", description = "字典接口")
public class DictController {
    @Autowired
    private DictService dictService;

    @PostMapping("/seletAll")
    @ApiModelProperty(value = "",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", required = false, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页显示数量", required = false, dataType = "int")
    })
    public ResponseEntity RseletAll(@RequestParam(required = false) Integer id,
                                    @RequestParam(required = false) Integer key,
                                    @RequestParam(required = false) Integer type,
                                    @RequestParam(defaultValue = "0") Integer pageNum,
                                    @RequestParam(defaultValue = "10") Integer pageSize){
        Map map = new HashMap();
        map.put("id",id);
        map.put("type",type);
        map.put("key",key);
        PageHelper.startPage(pageNum, pageSize);
        List<Dict> list = dictService.findByMap(map);
        PageInfo pageInfo = new PageInfo<>(list);
        return new ResponseEntity(ResponseResult.successResponse(pageInfo), HttpStatus.OK);
    }
}
