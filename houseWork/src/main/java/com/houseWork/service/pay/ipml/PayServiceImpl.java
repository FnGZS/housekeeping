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
		payOrder.setGoodsId(OrderUtils.getGoodsCode(payOrder.getEmployerId().longValue()));
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
