package com.houseWork.entity.dict;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@Table(name = "dict")
public class DictEntity {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "type")
    @ApiModelProperty(value = "所属类型")
    private String type;

    @Column(name = "k")
    @ApiModelProperty(value = "关键字")
    private String k;

    @Column(name = "v")
    @ApiModelProperty("值")
    private String v;

    @Column(name = "create_time")
    private Date createTime;
}
