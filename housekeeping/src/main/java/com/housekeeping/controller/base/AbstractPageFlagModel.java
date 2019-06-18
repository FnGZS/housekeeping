package com.housekeeping.controller.base;

/**
 * @author
 */

public abstract class AbstractPageFlagModel extends AbstractFlagModel {

    /**
     * 当前页数
     */
    private Integer pageNo = 0;

    /**
     * 分页�?
     */
    private Integer pageSize = 0;

    /**
     * 总分页数
     */
    private Integer pageCount = 0;

    /**
     * 总记录数
     */
    private Integer recordCount = 0;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
