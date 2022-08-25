package com.wyg.teach.api.domain;

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
 * 学生作业对象 teach_student_homework
 *
 * @author WorrilessGo
 * @date 2022-08-12
 */
@ApiModel(description = "学生作业")
@Data
@ToString
public class TeachStudentHomework extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 所属院校
     */
    @ApiModelProperty(value = "所属院校", required = false)
    @Excel(name = "所属院校")
    private Long collegeId;

    /**
     * 班级ID
     */
    @ApiModelProperty(value = "班级ID", required = false)
    @Excel(name = "班级ID")
    private Long classId;

    /**
     * 阶段ID
     */
    @ApiModelProperty(value = "阶段ID", required = false)
    @Excel(name = "阶段ID")
    private Long stageId;

    /**
     * 所属科目
     */
    @ApiModelProperty(value = "所属科目", required = false)
    @Excel(name = "所属科目")
    private Long courseId;

    /**
     * 班级作业ID
     */
    @ApiModelProperty(value = "班级作业ID", required = false)
    @Excel(name = "班级作业ID")
    private Long homeworkId;

    /**
     * 发布教师
     */
    @ApiModelProperty(value = "发布教师", required = false)
    @Excel(name = "发布教师")
    private Long teacherId;

    /**
     * 作答学生
     */
    @ApiModelProperty(value = "作答学生", required = false)
    @Excel(name = "作答学生")
    private Long studentId;

    /**
     * 附件
     */
    @ApiModelProperty(value = "附件", required = false)
    @Excel(name = "附件")
    private String attachments;

    /**
     * 试卷作答记录ID
     */
    @ApiModelProperty(value = "试卷作答记录ID", required = false)
    @Excel(name = "试卷作答记录ID")
    private Long paperRecordId;

    /**
     * 答案
     */
    @ApiModelProperty(value = "答案", required = false)
    @Excel(name = "答案")
    private String answer;

    /**
     * 分数
     */
    @ApiModelProperty(value = "分数", required = false)
    @Excel(name = "分数")
    private Double score;

    /**
     * 结果
     */
    @ApiModelProperty(value = "结果", required = false)
    @Excel(name = "结果")
    private String result;

    /**
     * 提交状态
     */
    @ApiModelProperty(value = "提交状态", required = false)
    @Excel(name = "提交状态")
    private String status;



    private SysDept college;

    private TeachClasses classes;

    private TeachClassHomework classesHomework;

    private TeachCourse course;

    private TeachTeacher teacher;

    private TeachStudent student;

}