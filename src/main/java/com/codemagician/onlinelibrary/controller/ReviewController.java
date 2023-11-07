package com.codemagician.onlinelibrary.controller;

import com.codemagician.onlinelibrary.domain.req.LeaveReviewReq;
import com.codemagician.onlinelibrary.domain.rsp.ReviewWithUserInfoRsp;
import com.codemagician.onlinelibrary.common.enums.MsgEnum;
import com.codemagician.onlinelibrary.security.jwt.JwtUtils;
import com.codemagician.onlinelibrary.service.ReviewService;
import com.codemagician.onlinelibrary.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 15:58
 */

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * find reviews by book Id
     * /api/reviews?bookId=?&page=?&size=?
     *
     * @param bookId
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity findReviewByBookId(@RequestParam Long bookId, Pageable pageable) {
        Page<ReviewWithUserInfoRsp> reviews = reviewService.findByBookId(bookId, pageable);

        return ResponseWrapper.build(MsgEnum.SUCCESS.getMsg(), HttpStatus.OK, reviews);
    }

    /**
     * check whether user had comment
     * /api/reviews/secure/iscomment/byuser?bookId=?
     *
     * @param bookId
     * @return
     */
    @GetMapping("/secure/iscomment/byuser")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity isCommentByUser(@RequestParam Long bookId) {
        Long userId = jwtUtils.getUserIdFromContext();
        Boolean isReviewLeftByUser = reviewService.isReviewLeftByUser(bookId, userId);

        return ResponseWrapper.build(MsgEnum.SUCCESS.getMsg(), HttpStatus.OK, isReviewLeftByUser);
    }

    @PostMapping("/secure/comment")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity leaveReview(@RequestBody LeaveReviewReq req) {
        Long userId = jwtUtils.getUserIdFromContext();
        Boolean isSuccess = reviewService.leaveReview(req, userId);

        return ResponseWrapper.build(MsgEnum.SUCCESS.getMsg(), HttpStatus.OK, isSuccess);
    }

}
