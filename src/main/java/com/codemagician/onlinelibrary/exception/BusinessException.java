package com.codemagician.onlinelibrary.exception;

import com.codemagician.onlinelibrary.enums.MsgEnum;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/26 15:18
 *
 * exception for business
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -5709149981778994972L;

    public BusinessException() {
        super(MsgEnum.ERROR.toString());
    }

    public BusinessException(String message) {
        super(MsgEnum.ERROR.msg(message));
    }

    public BusinessException(String message, String detailMsg) {
        super(MsgEnum.ERROR.msg(message, detailMsg));
    }

    public BusinessException(MsgEnum ienum) {
        super(ienum.toString());
    }
}
