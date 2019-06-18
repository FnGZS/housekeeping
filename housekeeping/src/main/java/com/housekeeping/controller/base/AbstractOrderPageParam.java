package com.housekeeping.controller.base;

/**
 * @author
 */
public class AbstractOrderPageParam extends AbstractPageParam {
	
    /**
     * 排序规则
     * 1:创建时间顺序
     * 2:创建时间倒序
     * 3:修改时间顺序
     * 4:修改时间倒序
     */
    private Integer orderByType;

	public Integer getOrderByType() {
		return orderByType;
	}

	public void setOrderByType(Integer orderByType) {
		this.orderByType = orderByType;
	}
	
	public String getOrderBy() {
		String orderBy = "gmt_created";
		Integer orderByType = this.orderByType;
		if(orderByType != null) {
			if(orderByType.intValue() == 3 || orderByType.intValue() == 4) {
				orderBy = "gmt_modified";
			}
		}
		return orderBy;
	}
	
	public String getDesc() {
		String desc = "desc";
		Integer orderByType = this.orderByType;
		if(orderByType != null && (orderByType.intValue() == 1 || orderByType.intValue() == 3)) {
			desc = "asc";
		}
		return desc;
	}
}
