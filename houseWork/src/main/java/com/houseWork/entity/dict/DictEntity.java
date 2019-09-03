package com.houseWork.entity.dict;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column(name = "key")
    @ApiModelProperty(value = "关键字")
    private String key;

    @Column(name = "value")
    @ApiModelProperty("值")
    private String value;
}
