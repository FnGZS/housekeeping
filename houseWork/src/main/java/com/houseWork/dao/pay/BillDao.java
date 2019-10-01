package com.houseWork.dao.pay;

import com.houseWork.entity.pay.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BillDao {
    void insertBill(Bill bill);
}
