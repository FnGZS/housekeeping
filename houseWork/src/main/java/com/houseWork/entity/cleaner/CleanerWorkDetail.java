package com.houseWork.entity.cleaner;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "cleaner_work")
public class CleanerWorkDetail {
    private Integer cid;
    private Integer appointmentId;
    private String workTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date workDate;
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
