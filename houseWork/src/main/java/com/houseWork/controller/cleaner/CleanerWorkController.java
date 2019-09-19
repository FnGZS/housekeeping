package com.houseWork.controller.cleaner;

import com.houseWork.entity.cleaner.CleanerWorkDetail;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.service.cleaner.CleanerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cleaner")
@Api(tags = "保洁员预约时间接口", description = "保洁员预约时间接口")
public class CleanerWorkController {

    @Autowired
    private CleanerService cleanerService;

    @PostMapping("/cleanerWork")
    @ApiOperation(value = "保洁员排班", notes = "保洁员排班")
    public ResponseEntity cleanerWork(@ApiParam("保洁员id") @RequestParam(value = "cleanerId", defaultValue = "") Integer cleanerId){
        List<CleanerWorkDetail> list = cleanerService.cleanerWork(cleanerId);
        return new ResponseEntity(ResponseResult.successResponse(list), HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    @ApiOperation(value = "预约")
    public ResponseEntity subscribe(@ApiParam("保洁员id") @RequestParam(value = "cleanerId", defaultValue = "") Integer cleanerId,
                                    @ApiParam("预约者id")  @RequestParam(value = "customerId", defaultValue = "") Integer customerId,
                                    @ApiParam("预约时间") @RequestParam(value = "workTime", defaultValue = "") String workTime){
        CleanerWorkDetail cleanerWorkDetail = new CleanerWorkDetail();
        cleanerWorkDetail.setCid(cleanerId);
        cleanerWorkDetail.setCustomerId(customerId);
        cleanerWorkDetail.setWorkTime(workTime);
        return new ResponseEntity(ResponseResult.successResponse(cleanerService.subscribe(cleanerWorkDetail)),HttpStatus.OK);
    }
}