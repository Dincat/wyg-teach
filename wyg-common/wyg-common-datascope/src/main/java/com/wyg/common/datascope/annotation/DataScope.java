package com.wyg.common.datascope.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限过滤注解
 * 
 * @author wyg
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 关联部门表字段的别名
     */
    public String deptFieldAlias() default "dept_id";

    /**
     * 主表别名
     * @return
     */
    public String primaryTableAlias() default "";

    /**
     * 关联用户表字段的别名
     */
    public String userFieldAlias() default "user_id";

}
