package com.housekeeping.service.base;

/**
 * @Type QueryResultDO
 * @Desc
 */
public class ResponsePageQueryDO<T> extends ResponseDO<T> {

    /** 默认每页显示的记录数 */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /** 每页�?多显示的记录�? */
    public static final int MAX_PAGE_SIZE = 200;
    /**
     * 总数
     */
    private int total = 0;

    /**
     * �?页大�?
     */
    private int pageSize;

    /**
     * 当前页数，从 0�?始，0代表第一�?
     */
    private int pageIndex;

    /**
     * 总页�?
     */
    private int totalPage;

    /**
     * 总数
     */
    public int getTotal() {
        return total;
    }
    

    /**
     * 得到总页�?
     * 
     * @return
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 总数
     */
    public void setTotal(int total) {
        this.totalPage = (total + getPageSize() - 1) / getPageSize();
        this.total = total;
    }

    /**
     * 获取�?页的记录�?
     * 
     * @return
     */
    public int getPageSize() {
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    /**
     * 设置�?页的记录�?
     * 
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 得到当前查询的第几页
     * 
     * @return
     */
    public int getPageIndex() {
        // if (pageIndex < 1) {
        // pageIndex = 0;
        // } else if (pageIndex > getTotalPage()) {
        // pageIndex = getTotalPage() - 1;
        // }

        return pageIndex;
    }

    /**
     * 设置当前页面
     * 
     * @param pageIndex
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
