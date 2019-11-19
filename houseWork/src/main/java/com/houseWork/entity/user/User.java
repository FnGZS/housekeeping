package com.houseWork.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.houseWork.entity.cleaner.Cleaner;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "open_id")
    @ApiModelProperty(value = "open_id")
    private String openId;

    @Column(name = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    @Column(name = "password")
    @ApiModelProperty(value = "用户密码")
    private String password;

    @Column(name = "telephone")
    @ApiModelProperty(value = "电话号码")
    private String telephone;

    @Column(name = "role")
    @ApiModelProperty(value = "用户角色")
    private String role;

    @Column(name = "examineStatus")
    @ApiModelProperty(value = "考核状态")
    private Integer examineStatus;

    @Column(name = "image")
    @ApiModelProperty(value = "用户头像")
    private String image;

    @Column(name = "sex")
    @ApiModelProperty(value = "性别")
    private Integer sex;

    @Column(name = "balance")
    @ApiModelProperty(value = "余额")
    private Integer balance;

    @Column(name = "creat_time")
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creatTime;

    private Cleaner cleaner;
}