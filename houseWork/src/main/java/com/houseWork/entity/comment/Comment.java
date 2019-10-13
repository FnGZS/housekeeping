package com.houseWork.entity.comment;

import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
public class Comment {
    private Integer id;
    private String senderId;
    private String content;
    private Date gmtCreated;
}
