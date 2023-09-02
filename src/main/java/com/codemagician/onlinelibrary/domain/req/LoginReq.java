package com.codemagician.onlinelibrary.domain.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/9/2 14:28
 */

@Data
public class LoginReq {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
