package com.houseWork.service.cleaner;

import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.cleaner.CleanerWorkDetail;

import java.util.List;

public interface CleanerService {

    /**
     * 添加保洁员
     * @param cleaner
     */
    void addCleaner(Cleaner cleaner);

    /**
     * 删除保洁员
     * @param list
     */
    void deleteCleaners(List<Cleaner> list);

    /**
     * 修改保洁员
     * @param list
     */
    void updateCleaners(List<Cleaner> list);

    /**
     * 查询保洁员（模糊）
     * @param namelike
     * @param sortName
     * @param sortOrder
     * @return
     */
    List<Cleaner> findCleaners(String namelike, String sortName, String sortOrder);

    /**
     * 查询保洁员（名称）
     * @param name
     * @return
     */
    Cleaner loadCleanerByName(String name);

    /**保洁员排班**/
    List<CleanerWorkDetail> cleanerWork(Integer cleanerId);
}
