package com.houseWork.controller.cleaner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.service.cleaner.CleanerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addCleaner(@RequestBody @ApiParam(value = "输入保洁员信息") Cleaner cleaner) {
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
    public ResponseEntity findCleaners(@RequestParam(required = false) String name, @RequestParam(required = false) String place,
                                       @RequestParam(required = false) Integer price, @RequestParam(required = false) Integer total,
                                       @RequestParam(defaultValue = "0") Integer pageNum,
                                       @RequestParam(defaultValue = "1000") Integer pageSize) {
        if (name != null && name.length() > 0) {
            name = name.replaceAll(" ", "");
        }
        List<Cleaner> list = cleanerService.findCleaners(name, place, price, total);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Cleaner> pageInfo = new PageInfo<>(list);
        return new ResponseEntity(ResponseResult.successResponse(pageInfo), HttpStatus.OK);
    }
}
