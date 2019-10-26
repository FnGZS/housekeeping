package com.houseWork.controller.appointment;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houseWork.entity.appointment.Appointment;
import com.houseWork.entity.cleaner.CleanerWorkDetail;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.service.appointment.AppointmentService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointment")
@Api(tags = "预约单接口")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private CleanerService cleanerService;

    @PostMapping("/add")
    @ApiOperation("添加预约")
    public ResponseEntity add (
            @RequestParam(value = "billingType", required = false, defaultValue = "") String billingType,
            @RequestParam(value = "customerId", required = false, defaultValue = "") Integer appointmentId,
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "phone", required = false, defaultValue = "")String phone,
            @RequestParam(value = "address", required = false, defaultValue = "")String address,
            @RequestParam(value = "area", required = false, defaultValue = "")String area,
            @RequestParam(value = "remark", required = false, defaultValue = "")String remark,
            @RequestParam(value = "clearnerId", required = false, defaultValue = "")Integer cleanerId,
            @RequestParam(value = "appointmentTime", required = false, defaultValue = "")@ApiParam("预约时间") String appointmentTime
    ){
        Appointment appointment = new Appointment();
        appointment.setBillingType(billingType);
        appointment.setName(name);
        appointment.setPhone(phone);
        appointment.setAddress(address);
        appointment.setArea(area);
        appointment.setRemark(remark);
        appointment.setCleanerId(cleanerId);
        appointment = appointmentService.add(appointment);

        CleanerWorkDetail cleanerWorkDetail = new CleanerWorkDetail();
        cleanerWorkDetail.setCid(cleanerId);
        cleanerWorkDetail.setAppointmentId(appointmentId);
        cleanerWorkDetail.setWorkTime(appointmentTime);
        cleanerService.subscribe(cleanerWorkDetail);
        return new ResponseEntity(ResponseResult.successResponse(appointmentService.getById(appointment.getId())), HttpStatus.OK);
    }

    @PostMapping("/all")
    @ApiOperation("查询预约单列表")
    public ResponseEntity all(
            @RequestParam(value = "billingType", required = false, defaultValue = "") String billingType,
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "clearnerId", required = false, defaultValue = "")Integer cleanerId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "20") int size
    ){
        Map map = new HashMap();
        PageHelper.startPage(page,size);
        map.put("billingType",billingType);
        map.put("name",name);
        map.put("cleanerId",cleanerId);
        List<Appointment> list = appointmentService.getAll(map);
        PageInfo pageInfo = new PageInfo(list);
        return new ResponseEntity(ResponseResult.successResponse(pageInfo), HttpStatus.OK);
    }

}
