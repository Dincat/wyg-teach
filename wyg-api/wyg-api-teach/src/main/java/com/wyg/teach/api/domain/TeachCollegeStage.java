package com.wyg.teach.api.domain;

import com.wyg.system.api.domain.SysDept;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wyg.common.core.annotation.Excel;
import com.wyg.common.core.web.domain.BaseEntity;

/**
 * 学期阶段对象 teach_college_stage
 *
 * @author WorrilessGo
 * @date 2022-07-21
 */
@ApiModel(description = "学期阶段")
@Data
@ToString
public class TeachCollegeStage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 阶段ID
     */
    private Long id;

    /**
     * 阶段名称
     */
    @ApiModelProperty(value = "阶段名称", required = false)
    @Excel(name = "阶段名称")
    private String stageName;

    /**
     * 阶段编码
     */
    @ApiModelProperty(value = "阶段编码", required = false)
    @Excel(name = "阶段编码")
    private String stageCode;

    /**
     * 学制类型
     */
    @ApiModelProperty(value = "学制类型", required = false)
    @Excel(name = "学制类型")
    private String eduSystem;

    /**
     * 学院ID
     */
    @ApiModelProperty(value = "学院ID", required = false)
    @Excel(name = "学院ID")
    private Long collegeId;

    /**
     * 阶段状态
     */
    @ApiModelProperty(value = "阶段状态", required = false)
    @Excel(name = "阶段状态")
    private String status;

    private SysDept college;

}