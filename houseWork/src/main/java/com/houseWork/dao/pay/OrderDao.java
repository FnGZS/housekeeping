package com.houseWork.dao.pay;

import org.apache.ibatis.annotations.Mapper;

import com.houseWork.entity.weixin.Order;

import net.sf.jsqlparser.statement.update.Update;

/**
 * @author zzc
 */
@Mapper
public interface OrderDao {

	/**
	 * 新增微信订单信息
	 */
	void insertOrder(Order order);
}
