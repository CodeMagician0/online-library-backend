package com.codemagician.onlinelibrary.enums;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 17:47
 */
public enum MsgEnum {

    SYSTEM_ERROR(500, "System Error");

    private final int code;
    private String msg;
    private MsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
