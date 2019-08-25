package com.houseWork.dao.pay;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.houseWork.entity.pay.PayOrder;

import net.sf.jsqlparser.statement.insert.Insert;

@Mapper
public interface PayOrderDao {
	/**
	 * 根据订单号获取订单，单个
	 * @param orderId 订单id
	 * @return 订单详情
	 */
	PayOrder getPayOrderById(int orderId);
	/**
	 * 根据清洁工获取订单（groupby goodsId）
	 * @param 情节工id
	 * @return 商品订单列表
	 */
	List<PayOrder> listPayOrderByClearnerId(int clearnerId);
	/**
	 * 根据雇主获取订单（groupby goodsId）
	 * @param 雇主id
	 * @return 商品订单列表
	 */
	List<PayOrder> listPayOrderByEmployerId(int employerId);
	/**
	 * 根据商品id获取订单（因为可能存在定金，尾款，所以用list接收）
	 * @param 商品id
	 * @return 订单列表
	 */
	List<PayOrder> listPayOrderByGoodsId(int goodsId);
	/**
	 * 更新订单信息（删除同样也是更新，假删除）
	 * @param payOrder 订单实体
	 */
	void updatePayOrder(PayOrder payOrder);
	/**
	 * 插入订单信息
	 * @param payOrder 订单实体
	 */
	void insertPayOrder(PayOrder payOrder);
}
