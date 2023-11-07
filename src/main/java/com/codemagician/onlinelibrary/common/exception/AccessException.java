package com.codemagician.onlinelibrary.common.exception;

import com.codemagician.onlinelibrary.common.enums.MsgEnum;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/29 16:23
 *
 * exception for security and authorization
 */
public class AccessException extends RuntimeException {

    private static final long serialVersionUID = 891168742033342843L;

    public AccessException() {
        super(MsgEnum.AUTH_ERROR.toString());
    }

    public AccessException(String message) {
        super(MsgEnum.AUTH_ERROR.msg(message));
    }

    public AccessException(MsgEnum ienum) {
        super(ienum.toString());
    }

}
