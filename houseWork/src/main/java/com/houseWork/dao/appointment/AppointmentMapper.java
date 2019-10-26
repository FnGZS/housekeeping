package com.houseWork.dao.appointment;

import com.houseWork.entity.appointment.Appointment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AppointmentMapper {

    @Insert(" INSERT INTO appointment(billing_type,name,phone,address,area,appointment_time,remark,cleanerId) " +
            " VALUES #{billingType}, #{name}, #{phone}, #{address}, #{area}, #{appointment_time}, #{remark}, #{cleanerId}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insert(Appointment appointment);

    @SelectProvider(type = AppointmentProvider.class, method = "select")
    @Results(id = "appointmentMore", value = {
            @Result(property = "cleanerWork", column = "select_cleaner", one = @One(select = "com.houseWork.dao.cleaner.CleanerDao.getCleanerById")),
            @Result(property = "cleaner", column = "select_appointment", one = @One(select = "com.houseWork.dao.cleaner.CleanerDao.getByAppointmentId")),
    })
    List<Appointment> getAll(Map map);

    @Select("SELECT *, cleaner_id AS select_cleaner, id AS select_appointment FROM appointment WHERE id = #{id} ")
    @ResultMap("appointmentMore")
    Appointment findById(Integer id);
}
