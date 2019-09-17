package com.houseWork.controller.cleaner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.service.cleaner.CleanerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cleaner")
@Api(tags = "保洁员接口", description = "保洁员接口")
public class CleanerController {

    @Autowired
    private CleanerService cleanerService;

    @PostMapping(value = "/addCleaner")
    @ApiOperation(value = "添加保洁员", notes = "添加保洁员")
    public ResponseEntity addCleaner(@RequestBody @Validated @ApiParam(value = "输入保洁员信息") Cleaner cleaner) {
        Cleaner cleaner1 = cleanerService.loadCleanerByName(cleaner.getName());
        if (cleaner1 == null) {
            cleanerService.addCleaner(cleaner);
            cleaner1 = cleanerService.loadCleanerByName(cleaner.getName());
            return new ResponseEntity(ResponseResult.successResponse(cleaner1), HttpStatus.OK);
        } else {
            return new ResponseEntity(ResponseResult.errResponse("保洁员已存在"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/deleteCleaners")
    @ApiOperation(value = "删除保洁员", notes = "删除保洁员")
    public ResponseEntity deleteCleaners(@RequestBody @ApiParam(value = "输入保洁员信息") List<Cleaner> list) {
        cleanerService.deleteCleaners(list);
        return new ResponseEntity(ResponseResult.successResponse("删除成功"), HttpStatus.OK);
    }

    @PostMapping(value = "/updateMenus")
    @ApiOperation(value = "修改保洁员", notes = "修改保洁员")
    public ResponseEntity updateCleaners(@RequestBody @ApiParam(value = "输入保洁员信息") List<Cleaner> list) {
        cleanerService.updateCleaners(list);
        return new ResponseEntity(ResponseResult.successResponse(list), HttpStatus.OK);
    }

    @PostMapping(value = "/findMenus")
    @ApiOperation(value = "查询保洁员", notes = "查询保洁员")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "namelike", value = "like条件", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sortName", value = "排序条件", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sortOrder", value = "排序规则", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页显示数量", dataType = "Integer")})
    public ResponseEntity findCleaners(@RequestParam(required = false) String namelike, @RequestParam(required = false) String sortName,
                                    @RequestParam(required = false) String sortOrder, @RequestParam(defaultValue = "0") Integer pageNum,
                                    @RequestParam(defaultValue = "1000") Integer pageSize) {
        if (namelike != null) {
            namelike = namelike.replaceAll(" ", "");
            if (namelike.isEmpty()) {
                namelike = null;
            }
        }
        List<Cleaner> list = cleanerService.findCleaners(namelike, sortName, sortOrder);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Cleaner> pageInfo = new PageInfo<>(list);
        return new ResponseEntity(ResponseResult.successResponse(pageInfo), HttpStatus.OK);
    }
}
