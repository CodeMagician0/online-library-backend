package com.codemagician.onlinelibrary.domain.rsp;

import com.codemagician.onlinelibrary.domain.vo.ReviewVO;
import com.codemagician.onlinelibrary.domain.vo.UserVO;
import lombok.Data;

import java.util.Date;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/9/7 18:39
 */

@Data
public class ReviewWithUserInfoRsp {

    private Long id;

    private Date date;

    private double rating;

    private Long bookId;

    private String reviewDescription;

    private UserVO user;
}
