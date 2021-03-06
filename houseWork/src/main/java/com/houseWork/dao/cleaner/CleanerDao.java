package com.houseWork.dao.cleaner;

import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.cleaner.CleanerWorkDetail;
import com.houseWork.entity.dict.DictEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

@Repository
public interface CleanerDao extends Mapper<DictEntity>, MySqlMapper<DictEntity> {

    /**
     * 添加保洁员
     *
     * @param cleaner
     */
    void addCleaner(@Param("cleaner") Cleaner cleaner);

    /**
     * 删除保洁员
     *
     * @param id
     */
    void deleteCleaner(@Param("id") Integer id);

    /**
     * 修改保洁员
     *
     * @param list
     */
    void updateCleaners(@Param("list") List<Cleaner> list);

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
    List<Cleaner> findCleaners(@Param("name") String name, @Param("place") String place, @Param("price") Integer price, @Param("total") Integer total, @Param("type") String type);

    /**
     * 查询保洁员（名称）
     *
     * @param name
     * @return
     */
    Cleaner loadCleanerByName(@Param("name") String name);

    /**
     * 查询保洁员（id）
     *
     * @param id
     * @return
     */
    Cleaner loadCleanerById(@Param("id") Integer id);
    /**
     * 查询保洁员（id）关联中间表
     *
     * @param id
     * @return
     */
    Cleaner loadCleanerById2(@Param("id") Integer id);

    /**
     * 保洁员排班
     **/

    List<CleanerWorkDetail> cleanerWork(Integer cleanerId);

    @Insert("INSERT cid,appointment_id,creat_time,work_date,type INTO cleaner_work VALUES ( #{cid}, #{appointmentId}, now(), #{workDate}, #{type},)")
    CleanerWorkDetail subscribe(CleanerWorkDetail cleanerWorkDetail);

    @Select("SELECT * FROM cleaner_work WHERE appointment_id = #{appointmentId}")
    CleanerWorkDetail getByAppointmentId(Integer appointmentId);

    /**
     * 根据id获取保洁员
     *
     * @param id
     * @return
     */
    Cleaner getCleanerById(Integer id);

    /**
     * 申请保洁员
     *
     * @param cid
     * @param uid
     */
    void addUserCleaner(@Param("cid") Integer cid, @Param("uid") Integer uid);
}
