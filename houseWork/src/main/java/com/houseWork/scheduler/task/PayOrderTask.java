package com.houseWork.scheduler.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class PayOrderTask {
    /**
     * 自动退款
     * @throws InterruptedException
     */
    @Scheduled(cron = "* * 0/5  * * *")
    public void autoRefund(){

        //查询同意退款的商品与订单表联查（修改时间在一小时内），状态不为已退款状态的商品
        //判断退款形式
        //根据不同的退款形式退款，请求退款接口
        //退款成功的修改对应商品的状态
        //将退款记录插入数据库
        //打日志

    }
    /**
     * 自动增加清洁员账户余额
     * @throws InterruptedException
     */
    @Scheduled(cron = "* * 0/5  * * *")
    @Transactional(rollbackFor = Exception.class)
    public void autoPayToClearner(){

        //联表查询超过七天的已完成的订单并且账单没有打入的记录
        // 循环为美洁员增加余额
        // 更新账单记录
        //打日志
        //为雇主加积分

    }


}
