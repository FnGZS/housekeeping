package com.houseWork.entity.cleaner;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "cleaner")
@ApiModel
public class Cleaner implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(required = false)
    private Integer id;

    @Column(name = "name")
    @ApiModelProperty(value = "姓名", required = false)
    private String name;

    @Column(name = "telephone")
    @ApiModelProperty(value = "手机号", required = false)
    private String telephone;

    @Column(name = "show")
    @ApiModelProperty(value = "简介", required = false)
    private String show;

    @Column(name = "time")
    @ApiModelProperty(value = "工龄", required = false)
    private Double time;

    @Column(name = "total")
    @ApiModelProperty(value = "接单量", required = false)
    private Long total;

    @Column(name = "back")
    @ApiModelProperty(value = "退单数", required = false)
    private Long back;

    @Column(name = "score")
    @ApiModelProperty(value = "评分", required = false)
    private Double score;

    @Column(name = "place")
    @ApiModelProperty(value = "地点", required = false)
    private String place;

    @Column(name = "price")
    @ApiModelProperty(value = "评分", required = false)
    private Double price;

    @Column(name = "type")
    @ApiModelProperty(value = "类型", required = false)
    private Long type;

    @Column(name = "work")
    @ApiModelProperty(value = "排班", required = false)
    private Long work;
}