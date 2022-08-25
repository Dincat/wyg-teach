package com.wyg.teach.api.domain;

import com.wyg.system.api.domain.SysDept;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wyg.common.core.annotation.Excel;
import com.wyg.common.core.web.domain.BaseEntity;

/**
 * 课程信息对象 teach_course
 *
 * @author WorrilessGo
 * @date 2022-07-21
 */
@ApiModel(description = "课程信息")
@Data
@ToString
public class TeachCourse extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = false)
    @Excel(name = "主键")
    private Long id;

    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称", required = true)
    @Excel(name = "课程名称")
    private String courseName;

    /**
     * 学院ID
     */
    @ApiModelProperty(value = "学院ID", required = false)
    @Excel(name = "学院ID")
    private Long collegeId;

    /**
     * 学院
     */
    @ApiModelProperty(value = "学院", required = false)
    @Excel(name = "学院")
    private String collegeName;

    /**
     * 专业ID
     */
    @ApiModelProperty(value = "专业ID", required = false)
    @Excel(name = "专业ID")
    private Long majorId;

    /**
     * 专业
     */
    @ApiModelProperty(value = "专业", required = false)
    @Excel(name = "专业")
    private String majorName;

    /**
     * 老师ID
     */
    @ApiModelProperty(value = "老师ID", required = false)
    @Excel(name = "老师ID")
    private Long teacherId;

    /**
     * 课程描述
     */
    @ApiModelProperty(value = "课程描述", required = false)
    @Excel(name = "课程描述")
    private String courseDescription;

    /**
     * 封面
     */
    @ApiModelProperty(value = "封面", required = false)
    @Excel(name = "封面")
    private String cover;

    /**
     * 启用状态
     */
    @ApiModelProperty(value = "启用状态", required = false)
    @Excel(name = "启用状态")
    private String status;


    /**
     * 点击数
     */
    private Long hits;

    /**
     * 评分
     */
    @ApiModelProperty(value = "评分", required = false)
    @Excel(name = "评分")
    private Long rating;

    /**
     * 购买数
     */
    @ApiModelProperty(value = "购买数", required = false)
    @Excel(name = "购买数")
    private Long buyCount;

    /**
     * 课时数
     */
    @ApiModelProperty(value = "课时数", required = false)
    @Excel(name = "课时数")
    private Long period;

    /**
     * 首页显示
     */
    @ApiModelProperty(value = "首页显示", required = false)
    @Excel(name = "首页显示")
    private String showIndex;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示", required = false)
    @Excel(name = "是否显示")
    private String isShow;

    /**
     * 课程介绍
     */
    @ApiModelProperty(value = "课程介绍", required = false)
    @Excel(name = "课程介绍")
    private String introduce;

    private SysDept college;

}