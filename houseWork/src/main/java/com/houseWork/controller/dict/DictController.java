package com.houseWork.controller.dict;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houseWork.entity.dict.DictEntity;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.service.dict.DictService;
import io.swagger.annotations.*;
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
    @ApiModelProperty(value = "查找所有字典",notes = "查找所有字典")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "key", value = "key", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "type", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", required = false, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页显示数量", required = false, dataType = "int")
    })
    public ResponseEntity RseletAll(@RequestParam(required = false,defaultValue = "-1") Integer id,
                                    @RequestParam(required = false,defaultValue = "") String key,
                                    @RequestParam(required = false,defaultValue = "") String type,
                                    @RequestParam(defaultValue = "0") Integer pageNum,
                                    @RequestParam(defaultValue = "10") Integer pageSize){
        Map<String,Object> map = new HashMap();
        map.put("id",id);
        map.put("type",type);
        map.put("key",key);
        PageHelper.startPage(pageNum, pageSize);
        List<DictEntity> list = dictService.findByMap(map);
        PageInfo pageInfo = new PageInfo<>(list);
        return new ResponseEntity(ResponseResult.successResponse(pageInfo), HttpStatus.OK);
    }

    @PostMapping("/add")
    @ApiModelProperty(value = "添加字典",notes = "添加字典")
    public ResponseEntity addDict(@RequestParam(value = "type", required = false, defaultValue = "") String type,
                                  @RequestParam(value = "k", required = false, defaultValue = "") String k,
                                  @RequestParam(value = "v", required = false, defaultValue = "") String v){
        return new ResponseEntity(ResponseResult.successResponse(dictService.insert(DictEntity.builder()
                .type(type)
                .k(k)
                .v(v)
                .build())), HttpStatus.OK);
    }

    @PostMapping("/update")
    @ApiModelProperty(value = "修改字典",notes = "修改字典")
    public ResponseEntity updateDict(@RequestParam(value = "id", required = false, defaultValue = "") Integer id,
                                     @RequestParam(value = "type", required = false, defaultValue = "") String type,
                                     @RequestParam(value = "k", required = false, defaultValue = "") String k,
                                     @RequestParam(value = "v", required = false, defaultValue = "") String v){
        return new ResponseEntity(ResponseResult.successResponse(dictService.update(DictEntity.builder()
                .id(id)
                .type(type)
                .k(k)
                .v(v)
                .build())),HttpStatus.OK);
    }

    @PostMapping("/delete")
    @ApiModelProperty(value = "删除字典")
    public ResponseEntity deleteDict(@RequestParam(value = "id", required = true) Integer id){
        dictService.deleteById(id);
        return new ResponseEntity(ResponseResult.successResponse(),HttpStatus.OK);
    }

    @PostMapping("/dictDetail")
    @ApiModelProperty(value = "字典详情", notes = "字典详情")
    public ResponseEntity dictDetail(@RequestParam(value = "type", required = false, defaultValue = "") String type,
                                     @RequestParam(value = "k", required = false, defaultValue = "") String k){
        return new ResponseEntity(ResponseResult.successResponse(dictService.getDetail(type,k)),HttpStatus.OK);
    }

}
