package com.codemagician.onlinelibrary.domain.rsp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/9/2 14:51
 *
 * return when user login
 */

@Data
@AllArgsConstructor
public class JwtRsp {

    private Long id;

    private String token;

    private String tokenType;

    private String username;

    private String email;

    private List<String> roles;
}
