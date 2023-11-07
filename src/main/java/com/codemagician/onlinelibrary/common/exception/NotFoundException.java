package com.codemagician.onlinelibrary.exception;

import com.codemagician.onlinelibrary.enums.MsgEnum;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/26 15:06
 *
 * exception for data not found
 */
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5709149981778994972L;

    public NotFoundException() {
        super(MsgEnum.NOT_FOUND.toString());
    }
    public NotFoundException(String message) {
        super(MsgEnum.NOT_FOUND.msg(message));
    }

    /**
     * with verbose message
     * @param message
     * @param detailMsg
     */
    public NotFoundException(String message, String detailMsg) {
        super(MsgEnum.NOT_FOUND.msg(message, detailMsg));
    }

    /**
     * self-defined return code and message
     * @param ienum
     */
    public NotFoundException(MsgEnum ienum) {
        super(ienum.toString());
    }
}
