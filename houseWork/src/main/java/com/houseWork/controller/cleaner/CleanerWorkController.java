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

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cleaner")
@Api(tags = "保洁员预约时间接口", description = "保洁员预约时间接口")
public class CleanerWorkController {

    @Autowired
    private CleanerService cleanerService;

    @PostMapping("/cleanerWork")
    @ApiOperation(value = "保洁员排班", notes = "保洁员排班")
    public ResponseEntity cleanerWork(@ApiParam("保洁员id") @RequestParam(value = "cleanerId", defaultValue = "") Integer cleanerId) {
        List<CleanerWorkDetail> list = cleanerService.cleanerWork(cleanerId);
        return new ResponseEntity(ResponseResult.successResponse(list), HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    @ApiOperation(value = "预约")
    public ResponseEntity subscribe(@ApiParam("保洁员id") @RequestParam(value = "cleanerId", defaultValue = "") Integer cleanerId,
                                    @ApiParam("预约单id") @RequestParam(value = "customerId", defaultValue = "") Integer appointmentId,
                                    @ApiParam("日期") @RequestParam(value = "workDate", defaultValue = "") Date workDate,
                                    @ApiParam("上午状态") @RequestParam(value = "type", defaultValue = "") String type) {
        CleanerWorkDetail cleanerWorkDetail = new CleanerWorkDetail();
        cleanerWorkDetail.setCid(cleanerId);
        cleanerWorkDetail.setAppointmentId(appointmentId);
        cleanerWorkDetail.setWorkDate(workDate);
        cleanerWorkDetail.setType(type);
        return new ResponseEntity(ResponseResult.successResponse(cleanerService.subscribe(cleanerWorkDetail)), HttpStatus.OK);
    }
}
