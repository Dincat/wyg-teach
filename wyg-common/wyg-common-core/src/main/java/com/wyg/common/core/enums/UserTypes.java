package com.wyg.common.core.enums;

public enum  UserTypes {

    Administrator("00", "管理员"),
    Teacher("11", "教职师"),
    Student("33", "职工");

    private final String code;
    private final String info;

    UserTypes(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}