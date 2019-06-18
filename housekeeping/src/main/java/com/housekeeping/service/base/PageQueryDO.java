package com.housekeeping.service.base;

/**
 * @Type PageQueryDO
 * @Desc
 */
public class PageQueryDO {

    /**
     * 默认每页显示的记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 每页�?多显示的记录�?
     */
    public static final int MAX_PAGE_SIZE = 200;

    /**
     * �?页大�?
     */
    protected int pageSize;

    /**
     * 当前页数，从 0�?始，0代表第一�?
     */
    protected int pageIndex;

    /**
     * 获取�?页的记录�?
     * 
     * @return int
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
     * 设置当前页面
     * 
     * @param pageIndex
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }
    
    /**
     * 获取分页起始位置
     */
    public int getStartPos() {
    	return getPageIndex() * getPageSize();
    }
}
