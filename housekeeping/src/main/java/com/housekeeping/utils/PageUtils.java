package com.housekeeping.utils;

import com.housekeeping.controller.base.AbstractPageFlagModel;
import com.housekeeping.controller.base.AbstractPageParam;

public class PageUtils {
	
    public static <K extends AbstractPageFlagModel, T extends AbstractPageParam> void setPageModel(K pageModel,
            T pageParam, int record) {
        Integer pageNo = pageParam.getPageNo();
        Integer pageSize = pageParam.getPageSize();
        int pageCount = record % pageSize == 0 ? record / pageSize : (record / pageSize) + 1;
        if (pageNo <= 0) {
            pageNo = 1;
        }

        pageModel.setPageSize(pageParam.getPageSize());
        pageModel.setPageNo(pageNo);
        pageModel.setPageCount(pageCount);
        pageModel.setRecordCount(record);
    }

    /**
     * 重置分页Param
     * 
     * @param pageParam
     */
    public static <T extends AbstractPageParam> void resetPageParam(T pageParam) {
        Integer pageSize = pageParam.getPageSize();
        if (pageSize == null) {
            pageSize = 10;
        } else {
            if (pageSize > 100) {
                pageSize = 100;
            }
        }
        pageParam.setPageSize(pageSize);

        Integer pageNo = pageParam.getPageNo();
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        pageParam.setPageNo(pageNo);
    }
}
