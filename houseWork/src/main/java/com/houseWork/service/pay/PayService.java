package com.houseWork.service.pay;

import com.github.pagehelper.PageInfo;
import com.houseWork.entity.pay.PayOrder;
import com.houseWork.entity.pay.RefundApply;
import com.houseWork.entity.pay.SearchPayOrderParam;
import com.houseWork.entity.weixin.UserRefundInfo;

import java.util.List;

public interface PayService {
	/**
	 * 根据订单号获取订单，单个
	 * @param id 订单id
	 * @return 订单详情
	 */
	PayOrder getPayOrderById(String id);

	/**
	 * 根据商品id获取订单（因为可能存在定金，尾款，所以用list接收）
	 * @param goodsId 商品id
	 * @return 订单列表
	 */
	List<PayOrder> listPayOrderByGoodsId(String goodsId);
	/**
	 * 根据条件获取商品订单列表
	 * @param searchParam 搜索条件
	 * @return
	 */
	PageInfo<List<PayOrder>>  getPayOrderListByCondition(SearchPayOrderParam searchParam,int pageNum,int pageSize);
	/**
	 * 更新订单信息（删除同样也是更新，假删除）
	 * @param payOrder 订单实体
	 */
	void updatePayOrder(PayOrder payOrder);
	/**
	 * 新增订单信息
	 * @param payOrder 订单实体
	 */
	PayOrder insertPayOrder(PayOrder payOrder);
	
	/**
	 * 删除订单信息
	 * @param goodsId 订单实体
	 */
	void deletePayOrder(String goodsId);

	/**
	 * 申请退款
	 * @param refundApply 退款实体
	 * @return 订单详情
	 */
	void insertRefundApply(RefundApply refundApply);

	/**
	 * 判断是否已经申请了退款
	 * @param goodsId
	 * @return
	 */
    boolean getRefundApply(String goodsId);

	/**
	 * 获取同意退款的退款订单
	 * @return
	 */
	List<RefundApply> getRefundApplyToRefund();

	/**
	 * 插入退款记录
	 * @param refundApply
	 */
	void insertRefundOrder(UserRefundInfo refundApply);
}
