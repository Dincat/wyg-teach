package com.wyg.teach.api.domain;

import com.wyg.system.api.domain.SysDept;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wyg.common.core.annotation.Excel;
import com.wyg.common.core.web.domain.BaseEntity;

/**
 * 专业信息对象 teach_major
 *
 * @author WorrilessGo
 * @date 2022-07-21
 */
@ApiModel(description = "专业信息")
@Data
@ToString
public class TeachMajor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 专业ID
     */
    private Long id;

    /**
     * 专业名称
     */
    @ApiModelProperty(value = "专业名称", required = true)
    @Excel(name = "专业名称")
    private String majorName;

    /**
     * 专业类型
     */
    @ApiModelProperty(value = "专业类型", required = false)
    @Excel(name = "专业类型")
    private String majorType;

    /**
     * 学院ID
     */
    @ApiModelProperty(value = "学院ID", required = true)
    @Excel(name = "学院ID")
    private Long collegeId;

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
}