
package com.houseWork.controller.pay;

import com.alibaba.fastjson.JSON;
import com.houseWork.entity.pay.PayOrder;
import com.houseWork.entity.pay.RefundApply;
import com.houseWork.entity.pay.SearchPayOrderParam;
import com.houseWork.entity.response.ResponseResult;
import com.houseWork.entity.user.User;
import com.houseWork.entity.weixin.*;
import com.houseWork.service.pay.PayService;
import com.houseWork.service.user.UserService;
import com.houseWork.service.weixin.WeixinAppService;
import com.houseWork.service.weixin.domin.WeixinGeneralResult;
import com.houseWork.service.weixin.weixinApp.WeixinAppURL;
import com.houseWork.utils.DateUtil;
import com.houseWork.utils.OrderUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author zzc
 */
@Api(tags="订单及支付功能接口",description="订单及支付功能接口")
@RequestMapping("/pay")
@RestController
@Slf4j
public class PayController {
	@Autowired
	private PayService payService;
    @Autowired
    private UserService userService;
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
			return new ResponseEntity(result.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(result.getDataResult(),HttpStatus.OK);
	}
	@ApiOperation(value = "生成系统订单",notes = "生成系统订单")
	@PostMapping("/payOrder")
	public ResponseEntity<ResponseResult<PayOrder>> createPayOrder(@RequestBody  PayOrder payOrder ){
		return new ResponseEntity(ResponseResult.successResponse(payService.insertPayOrder(payOrder)), HttpStatus.OK);
	}
	@ApiOperation(value = "更新系统订单",notes = "更新系统订单")
	@PutMapping("/payOrder")
	public ResponseEntity<ResponseResult<String>> updatePayOrder(@RequestBody PayOrder payOrder){
		payService.updatePayOrder(payOrder);
		return new ResponseEntity(ResponseResult.successResponse(),HttpStatus.OK);
	}	
	@ApiOperation(value = "删除系统订单（假删除）",notes = "删除系统订单（假删除）")
	@DeleteMapping("/payOrder/{goodsId}")
	public ResponseEntity<ResponseResult<String>> deletePayOrder(@PathVariable @ApiParam("商品编号")String goodsId){
		payService.deletePayOrder(goodsId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@ApiOperation(value = "根据系统内部订单编号获取详情",notes = "根据系统内部订单编号获取详情")
	@GetMapping("/payOrder/{id}")
	public ResponseEntity<ResponseResult<PayOrder>> getPayOrderById(@PathVariable @ApiParam("系统内部编号")  String id){
		return new ResponseEntity(ResponseResult.successResponse(payService.getPayOrderById(id)),HttpStatus.OK);
	}
	@ApiOperation(value = "根据系统商品订单编号获取详情",notes = "根据系统商品订单编号获取详情")
	@GetMapping("/payOrder/goods/{goodsId}")
	public ResponseEntity<ResponseResult<List<PayOrder>>> getPayOrderByGoodsId(@PathVariable @ApiParam("商品编号") String goodsId){
		return new ResponseEntity(ResponseResult.successResponse(payService.listPayOrderByGoodsId(goodsId)),HttpStatus.OK);
	}
	@ApiOperation(value = "根据各种条件得到系统订单列表",notes = "根据各种条件得到系统订单列表")
	@GetMapping("/payOrder")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "int", name = "pageNum", value = "页码", dataType = "string",required=true),
			@ApiImplicitParam(paramType = "int", name = "pageSize", value = "显示条数", dataType = "string",required=true)})
	public ResponseEntity<ResponseResult<List<PayOrder>>> getPayOrderListByCondition(SearchPayOrderParam searchParam,@RequestParam(defaultValue = "0")int pageNum
			,@RequestParam(defaultValue = "10")int pageSize){
		return new ResponseEntity(ResponseResult.successResponse(payService.getPayOrderListByCondition(searchParam,pageNum,pageSize)), HttpStatus.OK);
	}
    @ApiOperation(value = "根据各种条件得到系统订单列表数量",notes = "根据各种条件得到系统订单列表数量")
    @GetMapping("/payOrder/count")
    public ResponseEntity<ResponseResult<Map>>getPayOrderListByCondition(SearchPayOrderParam searchParam){
        return new ResponseEntity(ResponseResult.successResponse(payService.getPayOrderListByCondition(searchParam)), HttpStatus.OK);
    }
	@ApiOperation(value = "提现",notes = "提现")
	@GetMapping("/cashWithdrawal")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "Integer", name = "cleanId", value = "保洁员id", dataType = "string",required=true),
            @ApiImplicitParam(paramType = "string", name = "cash", value = "提现金额", dataType = "string",required=true),
            @ApiImplicitParam(paramType = "string", name = "platCode", value = "微信提供的code", dataType = "string",required=true)})
	public ResponseEntity cashWithdrawal(Integer cleanId,double cash,String platCode,HttpServletRequest request){
        User user = userService.selectById(cleanId);
        if(user==null||user.getBalance()==0||user.getBalance().doubleValue()<cash){
            return new ResponseEntity("余额不足",HttpStatus.BAD_REQUEST);
        }
        EnterprisePayParam param = new EnterprisePayParam();
        param.setPlatCode(platCode);
        String orderId = OrderUtils.getTxCode(cleanId.longValue());
        //获取ip
        String ip = request.getRemoteAddr();
	    WeixinAppService.enterprisePay(param,orderId,ip);
        log.debug("提现:"+cleanId+":"+cash);
		return  new ResponseEntity("提现成功",HttpStatus.OK);
	}
    @ApiOperation(value = "申请退款",notes = "申请退款")
    @PostMapping("/refundApply")
    public ResponseEntity insertRefundApply(@RequestBody  RefundApply refundApply){
	    if(!payService.getRefundApply(refundApply.getGoodsId())){
            return  new ResponseEntity("请勿重复提交",HttpStatus.BAD_REQUEST);
        }
	    //请勿重复提交
         payService.insertRefundApply(refundApply);
        return  new ResponseEntity(HttpStatus.OK);
    }
    @ApiOperation(value = "退单接口",notes = "退单接口")
    @PostMapping("/refundApply/{orderId}")
    public ResponseEntity refundOrder(@PathVariable String orderId){
        UserRefundParam param = new UserRefundParam();
        PayOrder order = payService.getPayOrderById(orderId);
        if(order==null){
            return  new ResponseEntity("订单不存在",HttpStatus.BAD_REQUEST);
        }
        //开始时间
        long startTime = DateUtil.getYmdTime(order.getStartTime()).getTime();
        //比较这单的服务时间和准备退单的时间
        if(startTime<=System.currentTimeMillis()){
            return  new ResponseEntity("不能退当天的单",HttpStatus.BAD_REQUEST);
		}
        param.setOrderId(order.getId());
        param.setRefundFee(order.getPayPrice());
        param.setTotalFee(order.getPayPrice());
        order.setOrderState(4);
        //如果是未付款订单
        if(order.getOrderState() ==1){
            //更新订单
            payService.updatePayOrder(order);
        }
        //如果是非未付款订单
        else {
            //退款
            WeixinGeneralResult result = WeixinAppService.refund(param);
            if(HttpStatus.BAD_REQUEST.equals(result.getCode())){
                log.error("退单失败:"+ JSON.toJSONString(order));
                return  new ResponseEntity("退单失败",HttpStatus.BAD_REQUEST);
            }
            //更新订单
            payService.updatePayOrder(order);
            payService.insertRefundOrder((UserRefundInfo) result.getDataResult());
        }
        log.debug("退单成功:"+ JSON.toJSONString(order));
        return  new ResponseEntity(HttpStatus.OK);
    }
}
