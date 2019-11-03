package com.houseWork.entity.cleaner;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.houseWork.entity.appointment.Appointment;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "cleaner_work")
public class CleanerWorkDetail {
    private Integer cid;
    private Integer appointmentId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date workDate;
    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    List<Appointment> appointments;
}
