package com.wyg.teach.api.domain;

import com.wyg.system.api.domain.SysDept;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wyg.common.core.annotation.Excel;
import com.wyg.common.core.web.domain.BaseEntity;

/**
 * 教职工信息对象 teach_teacher
 *
 * @author WorrilessGo
 * @date 2022-07-21
 */
@ApiModel(description = "教职工信息")
@Data
@ToString
public class TeachTeacher extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 教职工名称 */
    @ApiModelProperty(value = "教职工名称", required =true)
    @Excel(name = "教职工名称")
    private String teacherName;

    /** 教职工编码 */
    @ApiModelProperty(value = "教职工编码", required =true)
    @Excel(name = "教职工编码")
    private String teacherCode;

    /** 教职工类型 */
    @ApiModelProperty(value = "教职工类型", required =false)
    @Excel(name = "教职工类型")
    private String teacherType;

    /** 身份证号 */
    @ApiModelProperty(value = "身份证号", required =false)
    @Excel(name = "身份证号")
    private String idNumber;

    /** 出生日期 */
    @ApiModelProperty(value = "出生日期", required =false)
    @Excel(name = "出生日期")
    private String birthday;

    /** 学历 */
    @ApiModelProperty(value = "学历", required =false)
    @Excel(name = "学历")
    private String education;

    /** 学历 */
    @ApiModelProperty(value = "学历", required =false)
    @Excel(name = "学历")
    private String degree;

    /** 英语水平 */
    @ApiModelProperty(value = "英语水平", required =false)
    @Excel(name = "英语水平")
    private String englishLevel;

    /** 政治面貌 */
    @ApiModelProperty(value = "政治面貌", required =false)
    @Excel(name = "政治面貌")
    private Long politicsStatus;

    /** 用户邮箱 */
    @ApiModelProperty(value = "用户邮箱", required =false)
    @Excel(name = "用户邮箱")
    private String email;

    /** 电话 */
    @ApiModelProperty(value = "电话", required =false)
    @Excel(name = "电话")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱", required =false)
    @Excel(name = "邮箱")
    private String mail;

    /** qq号码 */
    @ApiModelProperty(value = "qq号码", required =false)
    @Excel(name = "qq号码")
    private String qq;

    /** 民族 */
    @ApiModelProperty(value = "民族", required =false)
    @Excel(name = "民族")
    private String nation;

    /** 省份 */
    @ApiModelProperty(value = "省份", required =false)
    @Excel(name = "省份")
    private String province;

    /** 省编码 */
    @ApiModelProperty(value = "省编码", required =false)
    @Excel(name = "省编码")
    private String provinceKey;

    /** 城市 */
    @ApiModelProperty(value = "城市", required =false)
    @Excel(name = "城市")
    private String city;

    /** 城市编码 */
    @ApiModelProperty(value = "城市编码", required =false)
    @Excel(name = "城市编码")
    private String cityKey;

    /** 区 */
    @ApiModelProperty(value = "区", required =false)
    @Excel(name = "区")
    private String district;

    /** 区编码 */
    @ApiModelProperty(value = "区编码", required =false)
    @Excel(name = "区编码")
    private String districtKey;

    /** 街道 */
    @ApiModelProperty(value = "街道", required =false)
    @Excel(name = "街道")
    private String town;

    /** 街道编码 */
    @ApiModelProperty(value = "街道编码", required =false)
    @Excel(name = "街道编码")
    private String townKey;

    /** 通讯地址 */
    @ApiModelProperty(value = "通讯地址", required =false)
    @Excel(name = "通讯地址")
    private String address;

    /** 用户性别（0男 1女 2未知） */
    @ApiModelProperty(value = "用户性别", required =false)
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 头像地址 */
    @ApiModelProperty(value = "头像地址", required =false)
    @Excel(name = "头像地址")
    private String avatar;

    /** 学院ID */
    @ApiModelProperty(value = "学院ID", required =false)
    @Excel(name = "学院ID")
    private Long collegeId;

    /** 学院 */
    @ApiModelProperty(value = "学院", required =false)
    @Excel(name = "学院")
    private String collegeName;

    /** 专业ID */
    @ApiModelProperty(value = "专业ID", required =false)
    @Excel(name = "专业ID")
    private Long majorId;

    /** 专业 */
    @ApiModelProperty(value = "专业", required =false)
    @Excel(name = "专业")
    private String majorName;

    /** 教职工状态（0在职 1离职） */
    @ApiModelProperty(value = "教职工状态", required =false)
    @Excel(name = "教职工状态", readConverterExp = "0=在职,1=离职")
    private String status;

    /** 用户表ID */
    @ApiModelProperty(value = "用户表ID", required =false)
    @Excel(name = "用户表ID")
    private Long userId;

    /** $column.columnComment */
    @ApiModelProperty(value = "毕业院校", required =false)
    @Excel(name = "毕业院校")
    private String graduateSchool;

    /** $column.columnComment */
    @ApiModelProperty(value = "毕业院校", required =false)
    @Excel(name = "毕业院校")
    private String graduateMajor;

    private SysDept college;

}