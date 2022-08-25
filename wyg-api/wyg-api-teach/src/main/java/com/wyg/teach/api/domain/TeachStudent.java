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
 * 学生信息对象 teach_student
 *
 * @author WorrilessGo
 * @date 2022-07-28
 */
@ApiModel(description = "学生信息")
@Data
@ToString
public class TeachStudent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    @Excel(name = "姓名")
    private String stuName;

    /**
     * 姓名拼音
     */
    @ApiModelProperty(value = "姓名拼音", required = false)
    @Excel(name = "姓名拼音")
    private String stuNamePinyin;

    /**
     * 学院ID
     */
    @ApiModelProperty(value = "学院ID", required = false)
    @Excel(name = "学院ID")
    private Long collegeId;

    /**
     * 班级ID
     */
    @ApiModelProperty(value = "班级ID", required = false)
    @Excel(name = "班级ID")
    private Long classId;

    /**
     * 班级名称
     */
    @ApiModelProperty(value = "班级名称", required = false)
    @Excel(name = "班级名称")
    private String className;

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
     * 学号
     */
    @ApiModelProperty(value = "学号", required = false)
    @Excel(name = "学号")
    private String stuCode;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", required = false)
    @Excel(name = "身份证号")
    private String idNumber;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址", required = false)
    @Excel(name = "头像地址")
    private String avatar;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = false)
    @Excel(name = "状态")
    private String status;

    /**
     * 学生类型
     */
    @ApiModelProperty(value = "学生类型", required = false)
    @Excel(name = "学生类型")
    private String studentType;

    /**
     * 学时
     */
    @ApiModelProperty(value = "学时", required = false)
    @Excel(name = "学时")
    private String period;

    /**
     * 性别  0=男,1=女,2=未知
     */
    @ApiModelProperty(value = "性别  0=男,1=女,2=未知", required = false)
    @Excel(name = "性别  0=男,1=女,2=未知")
    private String sex;

    /**
     * 民族
     */
    @ApiModelProperty(value = "民族", required = false)
    @Excel(name = "民族")
    private String nation;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期", required = false)
    @Excel(name = "出生日期")
    private String birthday;

    /**
     * 学历
     */
    @ApiModelProperty(value = "学历", required = false)
    @Excel(name = "学历")
    private String education;

    /**
     * 政治面貌
     */
    @ApiModelProperty(value = "政治面貌", required = false)
    @Excel(name = "政治面貌")
    private String politicsStatus;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", required = false)
    @Excel(name = "电话")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", required = false)
    @Excel(name = "邮箱")
    private String email;

    /**
     * qq号码
     */
    @ApiModelProperty(value = "qq号码", required = false)
    @Excel(name = "qq号码")
    private String qq;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份", required = false)
    @Excel(name = "省份")
    private String province;

    /**
     * 省编码
     */
    @ApiModelProperty(value = "省编码", required = false)
    @Excel(name = "省编码")
    private String provinceKey;

    /**
     * 城市
     */
    @ApiModelProperty(value = "城市", required = false)
    @Excel(name = "城市")
    private String city;

    /**
     * 城市编码
     */
    @ApiModelProperty(value = "城市编码", required = false)
    @Excel(name = "城市编码")
    private String cityKey;

    /**
     * 区
     */
    @ApiModelProperty(value = "区", required = false)
    @Excel(name = "区")
    private String district;

    /**
     * 区编码
     */
    @ApiModelProperty(value = "区编码", required = false)
    @Excel(name = "区编码")
    private String districtKey;

    /**
     * 街道
     */
    @ApiModelProperty(value = "街道", required = false)
    @Excel(name = "街道")
    private String town;

    /**
     * 街道编码
     */
    @ApiModelProperty(value = "街道编码", required = false)
    @Excel(name = "街道编码")
    private String townKey;

    /**
     * 通讯地址
     */
    @ApiModelProperty(value = "通讯地址", required = false)
    @Excel(name = "通讯地址")
    private String address;

    /**
     * 是否单亲家庭（0，否；1，是）
     */
    @ApiModelProperty(value = "是否单亲家庭", required = false)
    @Excel(name = "是否单亲家庭", readConverterExp = "0=，否；1，是")
    private String singleParent;

    /**
     * 学生来源   1=升学,2=口碑,3=社招,4=院校
     */
    @ApiModelProperty(value = "学生来源   1=升学,2=口碑,3=社招,4=院校", required = false)
    @Excel(name = "学生来源   1=升学,2=口碑,3=社招,4=院校")
    private String source1;

    /**
     * 学生来源   1=升学,2=口碑,3=社招,4=院校
     */
    @ApiModelProperty(value = "学生来源   1=升学,2=口碑,3=社招,4=院校", required = false)
    @Excel(name = "学生来源   1=升学,2=口碑,3=社招,4=院校")
    private String source2;

    /**
     * 学生来源   1=升学,2=口碑,3=社招,4=院校
     */
    @ApiModelProperty(value = "学生来源   1=升学,2=口碑,3=社招,4=院校", required = false)
    @Excel(name = "学生来源   1=升学,2=口碑,3=社招,4=院校")
    private String source3;

    /**
     * 英语水平 1=零基础,2=初级（学过26个字母等基本知识）,3=中级（系统学过英语，但掌握一般）,4=高级（四级水平）
     */
    @ApiModelProperty(value = "英语水平 1=零基础,2=初级", required = false)
    @Excel(name = "英语水平 1=零基础,2=初级", readConverterExp = "学=过26个字母等基本知识")
    private String englishLevel;

    /**
     * 学历性质  1=普通,2=成人,3=自考,4=网络
     */
    @ApiModelProperty(value = "学历性质  1=普通,2=成人,3=自考,4=网络", required = false)
    @Excel(name = "学历性质  1=普通,2=成人,3=自考,4=网络")
    private String eduBgType;

    /**
     * 毕业院校
     */
    @ApiModelProperty(value = "毕业院校", required = false)
    @Excel(name = "毕业院校")
    private String graduateSchool;

    /**
     * 毕业时间
     */
    @ApiModelProperty(value = "毕业时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "毕业时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date graduateDate;

    /**
     * 毕业专业
     */
    @ApiModelProperty(value = "毕业专业", required = false)
    @Excel(name = "毕业专业")
    private String graduateMajor;


    /**
     * 毕业专业
     */
    @ApiModelProperty(value = "毕业专业", required = false)
    @Excel(name = "毕业专业")
    private String employed;

    /**
     * 工作地
     */
    @ApiModelProperty(value = "工作地", required = false)
    @Excel(name = "工作地")
    private String jobLocal;

    /**
     * 就业意向
     */
    @ApiModelProperty(value = "就业意向", required = false)
    @Excel(name = "就业意向")
    private String jobWill;

    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编", required = false)
    @Excel(name = "邮编")
    private String zipCode;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 用户表ID
     */
    @ApiModelProperty(value = "用户表ID", required = false)
    @Excel(name = "用户表ID")
    private Long userId;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID", required = false)
    @Excel(name = "租户ID")
    private Long tenantId;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号", required = false)
    @Excel(name = "租户编号")
    private String tenantCode;

    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID", required = false)
    @Excel(name = "创建者ID")
    private Long createId;

    /**
     * 更新者ID
     */
    @ApiModelProperty(value = "更新者ID", required = false)
    @Excel(name = "更新者ID")
    private Long updateId;

    private SysDept college;

}