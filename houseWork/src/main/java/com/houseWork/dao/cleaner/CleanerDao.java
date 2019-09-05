package com.houseWork.dao.cleaner;

import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.dict.DictEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

@Repository
public interface CleanerDao extends Mapper<DictEntity>, MySqlMapper<DictEntity> {

    /**
     * 添加保洁员
     * @param cleaner
     */
    void addCleaner(@Param("cleaner") Cleaner cleaner);

    /**
     * 删除保洁员
     * @param list
     */
    void deleteCleaners(@Param("list") List<Cleaner> list);

    /**
     * 修改保洁员
     * @param list
     */
    void updateCleaners(@Param("list") List<Cleaner> list);

    /**
     * 查询保洁员（模糊）
     * @param namelike
     * @param sortName
     * @param sortOrder
     * @return
     */
    List<Cleaner> findCleaners(@Param("namelike") String namelike, @Param("sortName") String sortName, @Param("sortOrder") String sortOrder);

    /**
     * 查询保洁员（名称）
     * @param name
     * @return
     */
    Cleaner loadCleanerByName(@Param("name") String name);
}
