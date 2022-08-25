package com.wyg.common.core.enums;

public enum  PublishStatus {
    Published("0", "已发布"), Draft("1", "草稿");

    private final String code;
    private final String info;

    PublishStatus(String code, String info)
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
