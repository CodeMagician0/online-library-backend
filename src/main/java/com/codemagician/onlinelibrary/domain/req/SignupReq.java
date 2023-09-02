package com.codemagician.onlinelibrary.domain.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/9/2 14:30
 */

@Data
public class SignupReq {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

//    private Set<String> role;
}
