package com.codemagician.onlinelibrary.controller;

import com.codemagician.onlinelibrary.domain.req.LoginReq;
import com.codemagician.onlinelibrary.domain.req.SignupReq;
import com.codemagician.onlinelibrary.domain.rsp.JwtRsp;
import com.codemagician.onlinelibrary.enums.MsgEnum;
import com.codemagician.onlinelibrary.service.UserService;
import com.codemagician.onlinelibrary.util.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/9/2 14:12
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * /api/auth/signin
     * <p>
     * user sign in
     *
     * @param req
     * @return user details with jwt
     */
    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginReq req) {
        JwtRsp jwtRsp = userService.authenticateUser(req);

        return ResponseWrapper.build(MsgEnum.SUCCESS.getMsg(), HttpStatus.OK, jwtRsp);
    }

    /**
     * /api/auth/signup
     * <p>
     * user register (automatic login should be done by frontend calling signin api)
     *
     * @param req
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignupReq req) {
        userService.registerUser(req, false);

        return ResponseWrapper.build(MsgEnum.SUCCESS.getMsg(), HttpStatus.OK, "Registered Successfully");
    }

    /**
     * /api/atuh/signup/admin
     * <p>
     * backdoor api for admin signup
     *
     * @param req
     * @return
     */
    @PostMapping("/signup/admin")
    public ResponseEntity registerAdmin(@Valid @RequestBody SignupReq req) {
        userService.registerUser(req, true);

        return ResponseWrapper.build(MsgEnum.SUCCESS.getMsg(), HttpStatus.OK, "Admin Registered Successfully");
    }
}
