package com.codemagician.onlinelibrary.service;

import com.codemagician.onlinelibrary.domain.req.LoginReq;
import com.codemagician.onlinelibrary.domain.req.SignupReq;
import com.codemagician.onlinelibrary.domain.rsp.JwtRsp;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/9/2 14:14
 */
public interface UserService {

    /**
     * return jwt to login user
     *
     * @param req
     * @return
     */
    JwtRsp authenticateUser(LoginReq req);

    /**
     * register user
     *
     * @param req
     * @param isAdmin
     */
    void registerUser(SignupReq req, boolean isAdmin);
}
