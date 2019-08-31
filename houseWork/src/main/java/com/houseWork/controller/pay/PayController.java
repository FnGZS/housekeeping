
package com.houseWork.controller.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseWork.entity.pay.PayOrder;
import com.houseWork.entity.pay.SearchPayOrderParam;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.entity.weixin.OrderResponseInfo;
import com.houseWork.service.pay.PayService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javassist.expr.NewArray;
@Api(tags="支付功能模块",description="支付功能模块")
@RequestMapping("/pay")
@RestController
public class PayController {
	@Autowired
	private PayService payService;
	@ApiOperation(value = "统一调起支付",notes = "统一调起支付")
	@GetMapping("/wx/{payOrderId}")
	@ApiImplicitParam(paramType = "query", name = "payOrderId", value = "系统订单id", dataType = "String",required=true)	
	public ResponseEntity<ResponseResult<OrderResponseInfo>>  payOrder(@PathVariable String payOrderId){
		return null;	
	}
	@ApiOperation(value = "生成系统订单",notes = "生成系统订单")
	@PostMapping("/payOrder")
	public ResponseEntity<ResponseResult<PayOrder>> createPayOrder(PayOrder payOrder){
		return new ResponseEntity(payService.insertPayOrder(payOrder), HttpStatus.OK);
	}
	@ApiOperation(value = "更新系统订单",notes = "更新系统订单")
	@PutMapping("/payOrder")
	public ResponseEntity<ResponseResult<String>> updatePayOrder(PayOrder payOrder){
		payService.updatePayOrder(payOrder);
		return new ResponseEntity<ResponseResult<String>>(HttpStatus.OK);
	}	
	@ApiOperation(value = "删除系统订单",notes = "删除系统订单")
	@DeleteMapping("/payOrder/{goodsId}")
	public ResponseEntity<ResponseResult<String>> deletePayOrder(@PathVariable String goodsId){
		payService.deletePayOrder(goodsId);
		return new ResponseEntity<ResponseResult<String>>(HttpStatus.OK);
	}
	@ApiOperation(value = "根据系统内部订单编号获取详情",notes = "根据系统内部订单编号获取详情")
	@GetMapping("/payOrder/{id}")
	public ResponseEntity<ResponseResult<PayOrder>> getPayOrderById(@PathVariable String id){
		return new ResponseEntity(ResponseResult.successResponse(payService.getPayOrderById(id)),HttpStatus.OK);
	}
	@ApiOperation(value = "根据系统商品订单编号获取详情",notes = "根据系统商品订单编号获取详情")
	@GetMapping("/payOrder/goods")
	public ResponseEntity<ResponseResult<List<PayOrder>>> getPayOrderByGoodsId(@PathVariable String goodsId){
		return new ResponseEntity(ResponseResult.successResponse(payService.listPayOrderByGoodsId(goodsId)),HttpStatus.OK);
	}
	@ApiOperation(value = "根据各种条件得到系统订单列表",notes = "根据各种条件得到系统订单列表")
	@GetMapping("/payOrder")
	public ResponseEntity<ResponseResult<List<PayOrder>>> getPayOrderListByCondition(SearchPayOrderParam searchParam,int pageNum,int pageSize){	
		return new ResponseEntity(ResponseResult.successResponse(payService.getPayOrderListByCondition(searchParam,pageNum,pageSize)), HttpStatus.OK);
	}
	
}
