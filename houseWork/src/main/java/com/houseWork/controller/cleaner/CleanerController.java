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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping(value = "/updateCleanerWorkDetail")
    @ApiOperation(value = "修改保洁员排班", notes = "修改保洁员排班")
    public ResponseEntity updateCleanerWorkDetail(@RequestParam Integer cid, @RequestParam(required = false) Date workDate,
                                                  @RequestParam(required = false) String type) {
        Map map = new HashMap();
        map.put("id", cid);
        map.put("workDate", workDate);
        map.put("type", type);
        if (workDate == null) {
            cleanerService.deleteCleanerWorkDetail(map);
            return new ResponseEntity(ResponseResult.successResponse("删除成功"), HttpStatus.OK);
        }
        cleanerService.updateCleanerWorkDetail(map);
        return new ResponseEntity(ResponseResult.successResponse("修改成功"), HttpStatus.OK);
    }

    @PostMapping(value = "/updateCleaners")
    @ApiOperation(value = "修改保洁员", notes = "修改保洁员")
    public ResponseEntity updateCleaners(@RequestBody @ApiParam(value = "输入保洁员信息") List<Cleaner> list) {
        cleanerService.updateCleaners(list);
        return new ResponseEntity(ResponseResult.successResponse(list), HttpStatus.OK);
    }

    @PostMapping(value = "/findCleaners")
    @ApiOperation(value = "查询保洁员", notes = "查询保洁员")
    public ResponseEntity findCleaners(@RequestParam(required = false) String name, @RequestParam(required = false) String place,
                                       @RequestParam(required = false) Integer price, @RequestParam(required = false) Integer total,
                                       @RequestParam(defaultValue = "0") Integer pageNum,
                                       @RequestParam(defaultValue = "1000") Integer pageSize) {
        if (name != null && name.length() > 0) {
            name = name.replaceAll(" ", "");
        }
        if (place != null && place.length() > 0) {
            place = place.replaceAll(" ", "");
        }
        List<Cleaner> list = cleanerService.findCleaners(name, place, price, total);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Cleaner> pageInfo = new PageInfo<>(list);
        return new ResponseEntity(ResponseResult.successResponse(pageInfo), HttpStatus.OK);
    }

    @PostMapping(value = "/loadCleanerById")
    @ApiOperation(value = "通过id查保洁员详细信息", notes = "通过id查保洁员详细信息")
    public ResponseEntity loadCleanerById(@RequestParam Integer id) {
        Cleaner cleaner = cleanerService.loadCleanerById(id);
        return new ResponseEntity(ResponseResult.successResponse(cleaner), HttpStatus.OK);
    }
}
