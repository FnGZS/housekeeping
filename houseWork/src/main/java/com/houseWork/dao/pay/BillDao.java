package com.houseWork.dao.pay;

import com.houseWork.entity.pay.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BillDao {
    void insertBill(Bill bill);
    List<Bill> getBillList(Integer userId);
}
