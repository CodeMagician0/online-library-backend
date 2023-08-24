package com.codemagician.onlinelibrary.service.vo;

import lombok.Data;
import java.util.Date;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 16:07
 */

@Data
public class ReviewVO {

    private Long id;

    private String userEmail;

    private Date date;

    private double rating;

    private Long bookId;

    private String reviewDescription;
}
