package com.houseWork.dao.pay;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.houseWork.entity.pay.PayOrder;
import com.houseWork.entity.pay.SearchPayOrderParam;


@Mapper
public interface PayOrderDao {
	/**
	 * 根据订单号获取订单，单个
	 * @param orderId 订单id
	 * @return 订单详情
	 */
	PayOrder getPayOrderById(String  id);
	/**
	 * 根据搜索条件获取订单（groupby goodsId(商品id)）
	 * @param 条件实体
	 * @return 商品订单列表
	 */
	List<PayOrder> listPayOrderByCondition(SearchPayOrderParam param);
	/**
	 * 根据商品id获取订单（因为可能存在定金，尾款，所以用list接收）
	 * @param 商品id
	 * @return 订单列表
	 */
	List<PayOrder> listPayOrderByGoodsId(String goodsId);
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
