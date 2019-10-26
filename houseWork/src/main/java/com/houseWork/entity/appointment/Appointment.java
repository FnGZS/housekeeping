package com.houseWork.entity.appointment;

import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.cleaner.CleanerWorkDetail;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table
public class Appointment {
    private Integer id;
    private CleanerWorkDetail cleanerWork;
    private String billingType;
    private String name;
    private String phone;
    private String address;
    private String area;
    private String appointmentTime;
    private String remark;
    private Integer cleanerId;
    private Cleaner cleaner;

}
