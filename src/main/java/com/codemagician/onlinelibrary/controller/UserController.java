package com.codemagician.onlinelibrary.controller;

import com.codemagician.onlinelibrary.domain.vo.UserVO;
import com.codemagician.onlinelibrary.common.enums.MsgEnum;
import com.codemagician.onlinelibrary.security.jwt.JwtUtils;
import com.codemagician.onlinelibrary.service.UserService;
import com.codemagician.onlinelibrary.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/9/11 12:09
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/secure/refresh")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity refreshUser() {
        Long userId = jwtUtils.getUserIdFromContext();
        UserVO user = userService.getUser(userId);

        return ResponseWrapper.build(MsgEnum.SUCCESS.getMsg(), HttpStatus.OK, user);
    }
}
