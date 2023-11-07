package com.codemagician.onlinelibrary.security.jwt;

import com.codemagician.onlinelibrary.common.exception.AccessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/29 16:15
 */

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        throw new AccessException();
    }
}
