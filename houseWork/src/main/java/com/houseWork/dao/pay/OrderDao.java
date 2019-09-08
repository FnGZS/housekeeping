package com.houseWork.dao.pay;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author zzc
 */
@Mapper
@Repository
public interface OrderDao {

	/**
	 * 新增微信订单信息
	 */
	void insertOrder(Map map);
}
