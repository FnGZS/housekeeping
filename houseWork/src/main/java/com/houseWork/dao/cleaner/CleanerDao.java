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
     * @param list
     */
    void deleteCleaners(@Param("list") List<Cleaner> list);

    /**
     * 修改保洁员
     *
     * @param list
     */
    void updateCleaners(@Param("list") List<Cleaner> list);

    /**
     * 查询保洁员（模糊）
     *
     * @param name
     * @param place
     * @param price
     * @param total
     * @return
     */
    List<Cleaner> findCleaners(@Param("name") String name, @Param("place") String place, @Param("price") Integer price, @Param("total") Integer total);

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
     * 保洁员排班
     **/
    @Select("SELECT * FROM cleaner_work WHERE cid = #{cleanerId}")
    List<CleanerWorkDetail> cleanerWork(Integer cleanerId);

    @Insert("INSERT cid,appointment_id,work_time,creat_time,work_date,status INTO cleaner_work VALUES ( #{cid}, #{appointmentId}, #{workTime}, now(), #{workDate}, #{status})")
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
}
