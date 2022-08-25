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
 * 班级信息对象 teach_classes
 *
 * @author WorrilessGo
 * @date 2022-07-21
 */
@ApiModel(description = "班级信息")
@Data
@ToString
public class TeachClasses extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 班级ID
     */
    private Long id;

    /**
     * 班级名称
     */
    @ApiModelProperty(value = "班级名称", required = false)
    @Excel(name = "班级名称")
    private String className;

    /**
     * 班级编码
     */
    @ApiModelProperty(value = "班级编码", required = false)
    @Excel(name = "班级编码")
    private String classCode;

    /**
     * 班级类型
     */
    @ApiModelProperty(value = "班级类型", required = false)
    @Excel(name = "班级类型")
    private String classType;

    /**
     * 在读人数
     */
    @ApiModelProperty(value = "在读人数", required = false)
    @Excel(name = "在读人数")
    private Integer readingCount;

    /**
     * 退学人数
     */
    @ApiModelProperty(value = "退学人数", required = false)
    @Excel(name = "退学人数")
    private Integer dropOutCount;

    /**
     * 休学人数
     */
    @ApiModelProperty(value = "休学人数", required = false)
    @Excel(name = "休学人数")
    private Integer absenceCount;

    /**
     * 毕业人数
     */
    @ApiModelProperty(value = "毕业人数", required = false)
    @Excel(name = "毕业人数")
    private Integer levelCount;

    /**
     * 就业人数
     */
    @ApiModelProperty(value = "就业人数", required = false)
    @Excel(name = "就业人数")
    private Integer employmentCount;

    /**
     * 毕业人数
     */
    @ApiModelProperty(value = "毕业人数", required = false)
    @Excel(name = "毕业人数")
    private Integer graduateCount;

    /**
     * 当前阶段
     */
    @ApiModelProperty(value = "当前阶段", required = false)
    @Excel(name = "当前阶段")
    private String stage;

    /**
     * 开班时间
     */
    @ApiModelProperty(value = "开班时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开班时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结业时间
     */
    @ApiModelProperty(value = "结业时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结业时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 学院ID
     */
    @ApiModelProperty(value = "学院ID", required = false)
    @Excel(name = "学院ID")
    private Long collegeId;

    /**
     * 专业ID
     */
    @ApiModelProperty(value = "专业ID", required = false)
    @Excel(name = "专业ID")
    private Long majorId;

    /**
     * 班主任ID
     */
    @ApiModelProperty(value = "班主任ID", required = false)
    @Excel(name = "班主任ID")
    private Long teacherId;

    /**
     * 教职工状态（0在职 1离职）
     */
    @ApiModelProperty(value = "教职工状态", required = false)
    @Excel(name = "教职工状态", readConverterExp = "0=在职,1=离职")
    private String status;

    private SysDept college;

    private TeachTeacher teacher;

    private TeachMajor major;

}