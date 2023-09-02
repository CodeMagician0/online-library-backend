package com.codemagician.onlinelibrary.controller;

import com.codemagician.onlinelibrary.domain.req.LoginReq;
import com.codemagician.onlinelibrary.domain.req.SignupReq;
import com.codemagician.onlinelibrary.domain.rsp.JwtRsp;
import com.codemagician.onlinelibrary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/9/2 14:12
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    /**
     * /api/auth/signin
     *
     * user sign in
     *
     * @param req
     * @return user details with jwt
     */
    @PostMapping("/signin")
    public ResponseEntity<JwtRsp> authenticateUser(@Valid @RequestBody LoginReq req) {
        JwtRsp jwtRsp = userService.authenticateUser(req);

        return ResponseEntity.ok(jwtRsp);
    }

    /**
     * /api/auth/signup
     *
     * user register (automatic login should be done by frontend calling signin api)
     *
     * @param req
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupReq req) {
        userService.registerUser(req, false);

        return ResponseEntity.ok("User registered successfully");
    }

    /**
     * /api/atuh/signup/admin
     *
     * backdoor api for admin signup
     *
     * @param req
     * @return
     */
    @PostMapping("/signup/admin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody SignupReq req) {
        userService.registerUser(req, true);

        return ResponseEntity.ok("admin registered successfully");
    }
}
