package com.wyg.common.core.enums;

public enum  TeachClassStatus {
    Waiting("0", "待开课"), Begin("1", "已开课"), Over("2", "已毕业");

    private final String code;
    private final String info;

    TeachClassStatus(String code, String info)
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
