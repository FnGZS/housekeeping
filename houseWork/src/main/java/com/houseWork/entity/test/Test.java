package com.houseWork.entity.test;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "test_join")
public class Test {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "title")
    @ApiModelProperty(value = "标题")
    private String title;

    @Column(name = "answer")
    @ApiModelProperty(value = "答案")
    private String answer;

    @Column(name = "content")
    @ApiModelProperty(value = "内容")
    private String content;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
