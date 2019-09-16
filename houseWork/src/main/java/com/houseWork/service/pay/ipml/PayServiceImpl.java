package com.houseWork.service.pay.ipml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houseWork.dao.pay.PayOrderDao;
import com.houseWork.dao.user.UserDao;
import com.houseWork.entity.pay.PayOrder;
import com.houseWork.entity.pay.SearchPayOrderParam;
import com.houseWork.service.pay.PayService;
import com.houseWork.utils.OrderUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PayServiceImpl implements PayService{
	
	@Autowired
	private PayOrderDao payOrderDao;
	@Autowired UserDao userDao;
	@Override
	public PayOrder getPayOrderById(String id) {
		return getPayOrderDetailInfo(payOrderDao.getPayOrderById(id));
	}

	@Override
	public PageInfo<List<PayOrder>>  getPayOrderListByCondition(SearchPayOrderParam searchParam,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<PayOrder> payOrderList = payOrderDao.listPayOrderByCondition(searchParam);
		//判断是否为空
		if (CollectionUtils.isNotEmpty(payOrderList)) {
				for (PayOrder payOrder : payOrderList) {
				payOrder = getPayOrderDetailInfo(payOrder);
			}
		}
		PageInfo<List<PayOrder>>  pageInfo = new PageInfo<List<PayOrder>>();
		return pageInfo;
	}
	@Override
	public List<PayOrder> listPayOrderByGoodsId(String goodsId) {
		List<PayOrder> payOrderList = payOrderDao.listPayOrderByGoodsId(goodsId);
		if (CollectionUtils.isNotEmpty(payOrderList)) {
			for (PayOrder payOrder : payOrderList) {
				payOrder = getPayOrderDetailInfo(payOrder);
			}
		}
		return payOrderList;
	}

	@Override
	public void updatePayOrder(PayOrder payOrder) {
		payOrderDao.updatePayOrder(payOrder);
		
	}	
	@Override
	public PayOrder insertPayOrder(PayOrder payOrder) {
		String goodsCodeId = OrderUtils.getGoodsCode(payOrder.getEmployerId().longValue());
		//价格由后台生成
		payOrder.setOrderState(0);
		//总价
		double totalPrice = 0;
		//系数
		double coefficient = 0.3;
		//面积计算价格
		double areUnit  = 50;
		//时间计算价格
		double timeUnit  = 50;
		//判断计费类型
		//如果按面积
		if(payOrder.getChargingType()==0){
			totalPrice = payOrder.getArea()*areUnit;
		}
		//如果按时长
		if(payOrder.getChargingType()==1){
			totalPrice = payOrder.getLongTime()*timeUnit;
		}
		payOrder.setTotalPrice(totalPrice);
		//如果是开荒定金,生成30%的定金
		if(payOrder.getGoodsType()==1){
			payOrder.setPayPrice(totalPrice*coefficient);
		}
		else if(payOrder.getGoodsType()==2){
			payOrder.setPayPrice(totalPrice-totalPrice*coefficient);
		}
		else {
			payOrder.setPayPrice(totalPrice);
		}
		payOrder.setGoodsId(goodsCodeId);
		payOrder.setId(OrderUtils.getOrderCode(payOrder.getEmployerId().longValue()));
		payOrderDao.insertPayOrder(payOrder);
		return payOrder;	
	}

	@Override
	public void deletePayOrder(String goodsId) {
		PayOrder order = new PayOrder();
		order.setDelflag(1);
		order.setGoodsId(goodsId);
		payOrderDao.updatePayOrder(order);
	}
	/**
	 * 获取订单详细信息
	 * @param payOrder 系统订单实体
	 * @return 系统订单实体
	 */
	private PayOrder getPayOrderDetailInfo(PayOrder payOrder) {
		//获取保洁人员信息
		payOrder.setClearner(userDao.selectByPrimaryKey(payOrder.getClearnerId()));
		//获取业主信息
		payOrder.setEmployer(userDao.selectByPrimaryKey(payOrder.getEmployerId()));
		return payOrder;
	}


}
