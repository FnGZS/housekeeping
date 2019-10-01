package com.houseWork.service.pay.ipml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houseWork.dao.cleaner.CleanerDao;
import com.houseWork.dao.pay.PayOrderDao;
import com.houseWork.dao.user.UserDao;
import com.houseWork.entity.pay.PayOrder;
import com.houseWork.entity.pay.RefundApply;
import com.houseWork.entity.pay.SearchPayOrderParam;
import com.houseWork.entity.weixin.UserRefundInfo;
import com.houseWork.service.dict.DictService;
import com.houseWork.service.pay.PayService;
import com.houseWork.utils.OrderUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author zzc
 */
@Service
public class PayServiceImpl implements PayService{
	
	@Autowired
	private PayOrderDao payOrderDao;
	@Autowired
    private DictService dictService;
	@Autowired UserDao userDao;
	@Autowired
	private CleanerDao cleanerDao;
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
		//定金系数
		double coefficient = Double.parseDouble(dictService.getDetail("DJSX","DJKH").getV());

		//时间或面积计算价格
		double unitPrice  = payOrder.getUnitPrice();
		//判断计费类型
		//如果按面积
		if(payOrder.getChargingType()==0){
			totalPrice = payOrder.getArea()*unitPrice;
		}
		//如果按时长
		if(payOrder.getChargingType()==1){
			totalPrice = payOrder.getLongTime()*unitPrice;
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
		order.setDelFlag(1);
		order.setGoodsId(goodsId);
		payOrderDao.updatePayOrder(order);
	}

    @Override
    public void insertRefundApply(RefundApply refundApply) {
        payOrderDao.insertRefundApply(refundApply);
    }

    @Override
    public boolean getRefundApply(String goodsId) {

        return payOrderDao.getRefundApply(goodsId)==null;
    }

    @Override
    public List<RefundApply> getRefundApplyToRefund() {

        return payOrderDao.getRefundApplyToRefund();
    }

	@Override
	public void insertRefundOrder(UserRefundInfo refundApply) {
		payOrderDao.insertRefundOrder(refundApply);
	}

	/**
	 * 获取订单详细信息
	 * @param payOrder 系统订单实体
	 * @return 系统订单实体
	 */
	private PayOrder getPayOrderDetailInfo(PayOrder payOrder) {
		//获取保洁人员信息
		payOrder.setCleaner(null);
		//获取业主信息
		payOrder.setEmployer(userDao.selectByPrimaryKey(payOrder.getEmployerId()));
		return payOrder;
	}
}
