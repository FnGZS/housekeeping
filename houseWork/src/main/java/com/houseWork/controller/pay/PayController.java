
package com.houseWork.controller.pay;

import com.houseWork.entity.pay.PayOrder;
import com.houseWork.entity.pay.SearchPayOrderParam;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.entity.weixin.OrderResponseInfo;
import com.houseWork.entity.weixin.UserPayParam;
import com.houseWork.service.pay.PayService;
import com.houseWork.service.weixin.WeixinAppService;
import com.houseWork.service.weixin.domin.WeixinGeneralResult;
import com.houseWork.service.weixin.weixinApp.WeixinAppURL;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Api(tags="支付功能模块",description="支付功能模块")
@RequestMapping("/pay")
@RestController
public class PayController {
	@Autowired
	private PayService payService;

	@ApiOperation(value = "统一调起支付",notes = "统一调起支付")
	@GetMapping("/wx/{payOrderId}")
	@ApiImplicitParam(paramType = "query", name = "payOrderId", value = "系统订单id", dataType = "String",required=true)	
	public ResponseEntity<ResponseResult<OrderResponseInfo>>  payOrder(@PathVariable String payOrderId, UserPayParam param,HttpServletRequest request){
		//获取ip
		String ip = request.getRemoteAddr();
		PayOrder payOrder = payService.getPayOrderById(payOrderId);
		//获取订单金额
		param.setFee(payOrder.getPayPrice());
		WeixinGeneralResult<OrderResponseInfo> result= WeixinAppService.wxPay(param,ip,payOrderId, WeixinAppURL.NOTIFY_URL);
		//如果调起支付失败
		if(result.getCode().code!=HttpStatus.OK.value()){
			return new ResponseEntity("调起支付失败",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(result.getDataResult(),HttpStatus.OK);
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
	public ResponseEntity<ResponseResult<String>> deletePayOrder(@PathVariable @ApiParam("商品编号")String goodsId){
		payService.deletePayOrder(goodsId);
		return new ResponseEntity<ResponseResult<String>>(HttpStatus.OK);
	}
	@ApiOperation(value = "根据系统内部订单编号获取详情",notes = "根据系统内部订单编号获取详情")
	@GetMapping("/payOrder/{id}")
	public ResponseEntity<ResponseResult<PayOrder>> getPayOrderById(@PathVariable @ApiParam("系统内部编号")  String id){
		return new ResponseEntity(ResponseResult.successResponse(payService.getPayOrderById(id)),HttpStatus.OK);
	}
	@ApiOperation(value = "根据系统商品订单编号获取详情",notes = "根据系统商品订单编号获取详情")
	@GetMapping("/payOrder/goods")
	public ResponseEntity<ResponseResult<List<PayOrder>>> getPayOrderByGoodsId(@PathVariable @ApiParam("商品编号") String goodsId){
		return new ResponseEntity(ResponseResult.successResponse(payService.listPayOrderByGoodsId(goodsId)),HttpStatus.OK);
	}
	@ApiOperation(value = "根据各种条件得到系统订单列表",notes = "根据各种条件得到系统订单列表")
	@GetMapping("/payOrder")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "int", name = "页码", value = "页码", dataType = "String",required=true),
			@ApiImplicitParam(paramType = "int", name = "显示条数", value = "显示条数", dataType = "String",required=true)})
	public ResponseEntity<ResponseResult<List<PayOrder>>> getPayOrderListByCondition(SearchPayOrderParam searchParam,@RequestParam(defaultValue = "0")int pageNum
			,@RequestParam(defaultValue = "10")int pageSize){
		return new ResponseEntity(ResponseResult.successResponse(payService.getPayOrderListByCondition(searchParam,pageNum,pageSize)), HttpStatus.OK);
	}
	@ApiOperation(value = "提现",notes = "提现")
	@GetMapping("/cashWithdrawal")
	public ResponseEntity cashWithdrawal(String clearnId,double cash){
		String message = null;
		return  new ResponseEntity(message,HttpStatus.OK);

	}
	
}
