package com.houseWork.entity.news;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "news")
public class News {
    @Column(name = "nid")
    private Integer nid;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "creat_time")
    private Date creatTime;

    @Column(name = "is_recommend")
    private String isRecommend;
}
