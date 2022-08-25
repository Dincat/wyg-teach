package com.wyg.teach.api.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wyg.system.api.domain.SysDept;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wyg.common.core.annotation.Excel;
import com.wyg.common.core.web.domain.BaseEntity;

/**
 * 班级作业对象 teach_class_homework
 *
 * @author WorrilessGo
 * @date 2022-08-12
 */
@ApiModel(description = "班级作业")
@Data
@ToString
public class TeachClassHomework extends BaseEntity
{
    private static final long serialVersionUID=1L;

    /** ID */
    private Long id;

    /** 作业名称 */
    private String name;

    /** 所属院校 */
    @ApiModelProperty(value = "所属院校", required =false)
    @Excel(name = "所属院校")
    private Long collegeId;

    /** 布置老师 */
    private Long teacherId;

    /** 所属课程 */
    @ApiModelProperty(value = "所属课程", required =false)
    @Excel(name = "所属课程")
    private Long courseId;

    /** 班级ID */
    @ApiModelProperty(value = "班级ID", required =true)
    @Excel(name = "班级ID")
    private Long classId;

    /** 试卷ID */
    @ApiModelProperty(value = "试卷ID", required =false)
    @Excel(name = "试卷ID")
    private Long paperId;

    /** 所属阶段 */
    @ApiModelProperty(value = "所属阶段", required =false)
    @Excel(name = "所属阶段")
    private Long stageId;

    /** 作业描述 */
    @ApiModelProperty(value = "作业描述", required =false)
    @Excel(name = "作业描述")
    private String description;

    /** 作业类型 */
    @ApiModelProperty(value = "作业类型", required =false)
    @Excel(name = "作业类型")
    private String homeworkType;

    /** 发布状态 */
    @ApiModelProperty(value = "发布状态", required =false)
    @Excel(name = "发布状态")
    private String publishStatus;

    /** 是否公开 */
    @ApiModelProperty(value = "是否公开", required =false)
    @Excel(name = "是否公开")
    private String isPublic;

    /** 学生数量 */
    @ApiModelProperty(value = "学生数量", required =false)
    @Excel(name = "学生数量")
    private Integer studentCount;

    /** 提交数量 */
    @ApiModelProperty(value = "提交数量", required =false)
    @Excel(name = "提交数量")
    private Long submitCount;

    /** 截止时间 */
    @ApiModelProperty(value = "截止时间", required =false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadLine;

    /** 合格分数 */
    @ApiModelProperty(value = "合格分数", required =false)
    @Excel(name = "合格分数")
    private Long passScore;

    /** 良分数 */
    @ApiModelProperty(value = "良分数", required =false)
    @Excel(name = "良分数")
    private Long fineScore;

    /** 优分数 */
    @ApiModelProperty(value = "优分数", required =false)
    @Excel(name = "优分数")
    private Long goodScore;

    /** 满分 */
    @ApiModelProperty(value = "满分", required =false)
    @Excel(name = "满分")
    private Long fullScore;

    /** 不及格人数 */
    @ApiModelProperty(value = "不及格人数", required =false)
    @Excel(name = "不及格人数")
    private Long failCount;

    /** 及格人数 */
    @ApiModelProperty(value = "及格人数", required =false)
    @Excel(name = "及格人数")
    private Long passCount;

    /** 良人数 */
    @ApiModelProperty(value = "良人数", required =false)
    @Excel(name = "良人数")
    private Long fineCount;

    /** 优人数 */
    @ApiModelProperty(value = "优人数", required =false)
    @Excel(name = "优人数")
    private Long goodCount;

    /** 满分人数 */
    @ApiModelProperty(value = "满分人数", required =false)
    @Excel(name = "满分人数")
    private Long fullCount;

    /** 发布时间 */
    @ApiModelProperty(value = "发布时间", required =false)
    @Excel(name = "发布时间")
    private Date publishTime;

    private SysDept college;

    private TeachClasses classes;

    private TeachCourse course;

    private TeachTeacher teacher;


}