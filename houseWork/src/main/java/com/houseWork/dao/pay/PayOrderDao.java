package com.houseWork.dao.pay;

import com.houseWork.entity.pay.PayOrder;
import com.houseWork.entity.pay.RefundApply;
import com.houseWork.entity.pay.SearchPayOrderParam;
import com.houseWork.entity.weixin.UserRefundInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author zzc
 */
@Mapper
@Repository
public interface PayOrderDao {
	/**
	 * 根据订单号获取订单，单个
	 * @param id 订单id
	 * @return 订单详情
	 */
	PayOrder getPayOrderById(String  id);
	/**
	 * 根据搜索条件获取订单（groupby goodsId(商品id)）
	 * @param param 条件实体
	 * @return 商品订单列表
	 */
	List<PayOrder> listPayOrderByCondition(SearchPayOrderParam param);
	/**
	 * 根据商品id获取订单（因为可能存在定金，尾款，所以用list接收）
	 * @param goodsId 商品id
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

	/**
	 * 插入申请退款记录信息
	 * @param refundApply
	 */
	void insertRefundApply(RefundApply refundApply);

	/**
	 * 获取申请退款记录信息
	 * @param goodsId 商品编号
	 */
	RefundApply getRefundApply(String goodsId);

	/**
	 * 插入退款记录
	 * @param refundApply
	 */
	void insertRefundOrder(UserRefundInfo refundApply);

	/**
	 * 获取要退款的订单
	 * @return
	 */
	List<RefundApply> getRefundApplyToRefund();
	/**
	 * 将超时的已预定订单关闭
	 */
	void updateOrderStatusClose();
}

