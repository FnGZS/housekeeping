package com.houseWork.service.cleaner;

import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.cleaner.CleanerWorkDetail;

import java.util.List;
import java.util.Map;

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
     * @param id
     */
    void deleteCleaner(Integer id);

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
     * @param type
     * @return
     */
    List<Cleaner> findCleaners(String name, String place, Integer price, Integer total, String type);

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

    /**
     * 修改保洁员排班
     *
     * @param map
     */
    void updateCleanerWorkDetail(Map map);

    /**
     * 删除保洁员排班
     *
     * @param map
     */
    void deleteCleanerWorkDetail(Map map);

    CleanerWorkDetail subscribe(CleanerWorkDetail cleanerWorkDetail);

    /**
     * 申请保洁员
     *
     * @param cid
     * @param uid
     */
    void addUserCleaner(Integer cid, Integer uid);
}
