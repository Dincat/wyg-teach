package com.wyg.teach.api.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wyg.system.api.domain.SysDept;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wyg.common.core.annotation.Excel;
import com.wyg.common.core.web.domain.BaseEntity;

/**
 * 班级阶段对象 teach_class_stage
 *
 * @author WorrilessGo
 * @date 2022-07-29
 */
@ApiModel(description = "班级阶段")
@Data
@ToString
public class TeachClassStage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 班级ID
     */
    @ApiModelProperty(value = "院校ID", required = true)
    @Excel(name = "院校ID")
    private Long collegeId;

    /**
     * 班级ID
     */
    @ApiModelProperty(value = "班级ID", required = true)
    @Excel(name = "班级ID")
    private Long classId;

    /**
     * 阶段ID
     */
    @ApiModelProperty(value = "阶段ID", required = true)
    @Excel(name = "阶段ID")
    private Long stageId;

    /**
     * 阶段名称
     */
    @ApiModelProperty(value = "阶段名称", required = true)
    @Excel(name = "阶段名称")
    private String stageName;

    /**
     * 起始时间
     */
    @ApiModelProperty(value = "起始时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "起始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 阶段状态
     */
    @ApiModelProperty(value = "阶段状态", required = false)
    @Excel(name = "阶段状态")
    private String status;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 租户编号
     */
    private String tenantCode;

    /**
     * 创建者ID
     */
    private Long createId;

    /**
     * 更新者ID
     */
    private Long updateId;

    private SysDept college;

    private TeachClasses classes;



}