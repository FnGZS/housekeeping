package com.houseWork.service.cleaner;

import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.cleaner.CleanerWorkDetail;

import java.util.List;

public interface CleanerService {

    /**
     * 添加保洁员
     *
     * @param cleaner
     */
    void addCleaner(Cleaner cleaner);

    /**
     * 删除保洁员
     *
     * @param list
     */
    void deleteCleaners(List<Cleaner> list);

    /**
     * 修改保洁员
     *
     * @param list
     */
    void updateCleaners(List<Cleaner> list);

    /**
     * 查询保洁员（模糊）
     *
     * @param name
     * @param place
     * @param price
     * @param total
     * @return
     */
    List<Cleaner> findCleaners(String name, String place, Integer price, Integer total);

    /**
     * 查询保洁员（名称）
     *
     * @param name
     * @return
     */
    Cleaner loadCleanerByName(String name);

    /**
     * 查询保洁员（id）
     *
     * @param id
     * @return
     */
    Cleaner loadCleanerById(Integer id);

    /**
     * 保洁员排班
     **/
    List<CleanerWorkDetail> cleanerWork(Integer cleanerId);

    CleanerWorkDetail subscribe(CleanerWorkDetail cleanerWorkDetail);
}
