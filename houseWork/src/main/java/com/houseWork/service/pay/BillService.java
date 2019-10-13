package com.houseWork.service.pay;

import com.houseWork.entity.pay.Bill;

import java.util.List;

public interface BillService {
    /**
     * 插入订单
     * @param bill
     */
    void insertBill(Bill bill);

    /**
     * 根据userId获取账单
     * @param userId
     * @return
     */
    List<Bill> getBillList(Integer userId);
}
