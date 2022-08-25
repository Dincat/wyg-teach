package com.wyg.common.core.enums;

import cn.hutool.core.util.StrUtil;

public enum  DataScopeTypes {

    NONE("0","无数据权限"),
    ALL("1","全部数据权限"),
    CUSTOM("2","自定义数据权限"),
    DEPT("3","本部门数据权限"),
    DEPT_AND_CHILD("4","本部门及以下数据权限"),
    SELF("5","仅本人数据权限");

    private final String code;
    private final String info;

    DataScopeTypes(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public static DataScopeTypes getValue(String code){
        for(DataScopeTypes scope : values()){
            if(StrUtil.equals(code, scope.getCode())){
                return scope;
            }
        }
        return null;
    }

}
