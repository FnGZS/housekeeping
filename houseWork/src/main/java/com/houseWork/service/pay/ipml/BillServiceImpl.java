package com.houseWork.service.pay.ipml;

import com.houseWork.dao.pay.BillDao;
import com.houseWork.entity.pay.Bill;
import com.houseWork.service.pay.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillDao billDao;
    @Override
    public void insertBill(Bill bill) {
        billDao.insertBill(bill);
    }

    @Override
    public List<Bill> getBillList(Integer userId) {
        return billDao.getBillList(userId);
    }
}
