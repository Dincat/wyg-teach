package com.wyg.teach.api.domain;

import com.wyg.system.api.domain.SysDept;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wyg.common.core.annotation.Excel;
import com.wyg.common.core.web.domain.BaseEntity;

/**
 * 专业课程对象 teach_major_course
 *
 * @author WorrilessGo
 * @date 2022-07-29
 */
@ApiModel(description = "专业课程")
@Data
@ToString
public class TeachMajorCourse extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     * 院校ID
     */
    @ApiModelProperty(value = "院校ID", required = true)
    @Excel(name = "院校ID")
    private Long collegeId;

    /**
     * 专业ID
     */
    @ApiModelProperty(value = "专业ID", required = true)
    @Excel(name = "专业ID")
    private Long majorId;

    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID", required = true)
    @Excel(name = "课程ID")
    private Long courseId;

    /**
     * 课时数
     */
    @ApiModelProperty(value = "课时数", required = false)
    @Excel(name = "课时数")
    private Long period;

    /**
     * 所属阶段ID
     */
    @ApiModelProperty(value = "所属阶段ID", required = false)
    @Excel(name = "所属阶段ID")
    private Long stageId;

    private String stageName;

    /**
     * 课程状态
     */
    @ApiModelProperty(value = "课程状态", required = false)
    @Excel(name = "课程状态")
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


    private TeachCourse course;

    private SysDept college;



}