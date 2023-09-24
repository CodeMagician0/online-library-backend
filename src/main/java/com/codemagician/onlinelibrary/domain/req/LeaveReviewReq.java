package com.codemagician.onlinelibrary.domain.req;

import lombok.Data;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/9/24 11:41
 */

@Data
public class LeaveReviewReq {

    Double rating;

    Long bookId;

    String reviewDescription;
}
