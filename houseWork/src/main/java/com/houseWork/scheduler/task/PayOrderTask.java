package com.houseWork.scheduler.task;

import com.houseWork.dao.pay.PayOrderDao;
import com.houseWork.service.pay.PayService;
import com.houseWork.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PayOrderTask {
    @Autowired
    private UserService userService;
    @Autowired
    private PayService payService;
    @Autowired
    private PayOrderDao payOrderDao;

    /**
     * 自动退款
     *
     * @throws InterruptedException

    @Scheduled(cron = "* * 0/5  * * *")
    public void autoRefund() {

        //查询同意退款的商品（修改时间在6小时内），状态不为已付款状态的商品
        List<RefundApply> refundApplyList = payService.getRefundApplyToRefund();
        if (CollectionUtils.isNotEmpty(refundApplyList)) {
            UserRefundParam param;
            WeixinGeneralResult result;
            PayOrder payOrder;
            for (RefundApply refundApply : refundApplyList) {
                payOrder = new PayOrder();
                payOrder.setGoodsId(refundApply.getGoodsId());
                payOrder.setOrderState(3);
                //获取需要退款的商品
                List<PayOrder> orders = payService.listPayOrderByGoodsId(refundApply.getGoodsId());
                param = new UserRefundParam();
                //存在商品
                if (CollectionUtils.isNotEmpty(orders)) {
                    //判断退款形式  //根据不同的退款形式退款，请求退款接口
                    //如果退定金
                    if (refundApply.getState() == 2 && orders.size() == 2) {
                        param.setOrderId(orders.get(0).getId());
                        param.setRefundFee(orders.get(0).getPayPrice());
                        param.setTotalFee(orders.get(0).getPayPrice());
                        //退款
                        result = WeixinAppService.refund(param);
                        if (result.getCode().equals(HttpStatus.HTTP_OK)) {
                            payService.updatePayOrder(payOrder);
                            payOrderDao.insertRefundOrder((UserRefundInfo) result.getDataResult());
                        } else {
                            log.error(result.getMessage()+ JSONObject.toJSONString(orders));
                        }
                    }
                    //如果部分退款(如三成)
                    if (refundApply.getState() == 3) {
                        if (orders.size() == 1) {
                            param.setOrderId(orders.get(0).getId());
                            param.setRefundFee(orders.get(0).getPayPrice());
                            param.setTotalFee(orders.get(0).getPayPrice());
                        }
                        if (orders.size() == 2) {
                            param.setOrderId(orders.get(1).getId());
                            //部分退款退百分之30
                            param.setRefundFee(orders.get(1).getTotalPrice() * 0.3);
                            param.setTotalFee(orders.get(1).getPayPrice());
                        }
                        //退款
                        result = WeixinAppService.refund(param);
                        if (result.getCode().equals(HttpStatus.HTTP_OK)) {
                            payService.updatePayOrder(payOrder);
                            payOrderDao.insertRefundOrder((UserRefundInfo) result.getDataResult());
                        } else {
                            log.error(result.getMessage()+ JSONObject.toJSONString(orders));
                        }
                    }
                    //全额退款
                    if (refundApply.getState() == 4) {
                        if (orders.size() == 1) {
                            param.setOrderId(orders.get(0).getId());
                            param.setRefundFee(orders.get(0).getPayPrice());
                            param.setTotalFee(orders.get(0).getPayPrice());
                            //退款
                            result = WeixinAppService.refund(param);
                            if (result.getCode().equals(HttpStatus.HTTP_OK)) {
                                payService.updatePayOrder(payOrder);
                                payOrderDao.insertRefundOrder((UserRefundInfo) result.getDataResult());
                            } else {
                                log.error(result.getMessage()+ JSONObject.toJSONString(orders));
                            }
                        }
                        if (orders.size() == 2) {
                            param.setOrderId(orders.get(0).getId());
                            param.setRefundFee(orders.get(0).getPayPrice());
                            param.setTotalFee(orders.get(0).getPayPrice());
                            //退定金
                            result = WeixinAppService.refund(param);
                            if (result.getCode().equals(HttpStatus.HTTP_OK)) {
                                payOrderDao.insertRefundOrder((UserRefundInfo) result.getDataResult());
                                param.setOrderId(orders.get(1).getId());
                                param.setRefundFee(orders.get(1).getPayPrice());
                                param.setTotalFee(orders.get(1).getPayPrice());
                                //退尾款
                                result = WeixinAppService.refund(param);
                                //退款成功的修改对应商品的状态
                                if (result.getCode().equals(HttpStatus.HTTP_OK)) {
                                    payService.updatePayOrder(payOrder);
                                    //将退款记录插入数据库
                                    payOrderDao.insertRefundOrder((UserRefundInfo) result.getDataResult());
                                }
                            }
                            else {
                                log.error(result.getMessage()+ JSONObject.toJSONString(orders));
                            }
                        }
                    }
                }
                //打日志
                log.debug("自动退款："+JSONObject.toJSONString(orders));
            }
        }
    }
     */
    /**
     * 自动增加清洁员账户余额
     *
     * @throws InterruptedException

    @Scheduled(cron = "* * 0/5  * * *")
    @Transactional(rollbackFor = Exception.class)
    public void autoPayToClearner() {

        //联表查询超过七天的已完成的订单并且账单没有打入的记录
        // 循环为美洁员增加余额
        // 更新账单记录
        //打日志
        //为雇主加积分

    }

     */
}
