package com.houseWork.controller.pay;

import com.houseWork.entity.pay.Bill;
import com.houseWork.service.pay.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzc
 */
@RestController
@RequestMapping("/bill")
@Api(tags = "账单接口")
public class BillController {
    @Autowired
    private BillService billService;
    @GetMapping("/{userId}")
    public ResponseEntity getBillListByUserId(@PathVariable @ApiParam("用户id") Integer userId){
        billService.getBillList(userId);
        return new ResponseEntity(billService.getBillList(userId), HttpStatus.OK);
    }
    @PostMapping("/{userId}")
    public ResponseEntity createBillByUserId(@PathVariable @ApiParam("用户id") Integer userId, Bill bill){
        billService.getBillList(userId);
        return new ResponseEntity(billService.getBillList(userId), HttpStatus.OK);
    }
}
