package com.houseWork.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "quartz_job")
public class QuartzJob implements Serializable {


    public static final String JOB_KEY = "JOB_KEY";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    @NotNull(groups = {Update.class})
    private Long id;

    @Column(name = "job_name")
    @ApiModelProperty(value = "定时器名称")
    private String jobName;

    @Column(name = "bean_name")
    @NotBlank
    @ApiModelProperty(value = "bean名称")
    private String beanName;

    @Column(name = "method_name")
    @NotBlank
    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @Column(name = "params")
    @ApiModelProperty(value = "参数")
    private String params;

    @Column(name = "cron_expression")
    @NotBlank
    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    @Column(name = "is_pause")
    @ApiModelProperty(value = "状态(0、启动；1、不启动)")
    private Boolean isPause = false;

    @UpdateTimestamp
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    public interface Update {
    }
}
