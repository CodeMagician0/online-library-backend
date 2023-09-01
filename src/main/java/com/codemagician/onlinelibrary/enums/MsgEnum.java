package com.codemagician.onlinelibrary.enums;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 17:47
 */
public enum MsgEnum {

    SUCCESS(200, "Successful"),
    AUTH_ERROR(401, "Unauthorized"),
    ERROR(400, "Invalid Operation"),
    NOT_FOUND(404, "Resource Not Exist"),
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

    /**
     * concise message
     * @param msg
     * @return
     */
    public String msg(String msg) {
        return this.toJson(msg);
    }

    /**
     * with verbose message
     * @param msg
     * @param detailMsg
     * @return
     */
    public String msg(String msg, String detailMsg) {
        return this.toJson(msg, detailMsg);
    }

    private static final String JSON_CODE = "{\"code\":";
    private static final String JSON_MSG = ",\"msg\":\"";
    private static final String JSON_MSG_END = "\"}";
    private static final String JSON_DETAIL_MSG = "\",\"detailMsg\":";
    private static final String JSON_END = "}";

    @Override
    public String toString() {
        return JSON_CODE + this.getCode() + JSON_MSG + this.getMsg() + JSON_MSG_END;
    }

    public String toJson() {
        return JSON_CODE + this.getCode() + JSON_MSG + this.getMsg() + JSON_MSG_END;
    }

    private String toJson(String msg) {
        return JSON_CODE + this.getCode() + JSON_MSG + msg + JSON_MSG_END;
    }

    private String toJson(String msg, String detailMsg) {
        return JSON_CODE + this.getCode() + JSON_MSG + msg + JSON_DETAIL_MSG + detailMsg + JSON_END;
    }
}
