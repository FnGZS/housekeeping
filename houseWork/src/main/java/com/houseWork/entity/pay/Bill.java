package com.houseWork.entity.pay;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
public class Bill {
    private Integer id;
    private Integer userId;
    private double price ;
    private Integer type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreated;
}
