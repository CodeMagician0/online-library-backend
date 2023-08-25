package com.codemagician.onlinelibrary.controller;

import com.codemagician.onlinelibrary.service.ReviewService;
import com.codemagician.onlinelibrary.service.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * find reviews by book Id
     * /api/reviews/1?&page=?&size=?
     * @param bookId
     * @param pageable
     * @return
     */
    @GetMapping("/{bookId}")
    public ResponseEntity<Page<ReviewVO>> findReviewByBookId(@PathVariable Long bookId, Pageable pageable) {
        Page<ReviewVO> reviews = reviewService.findByBookId(bookId, pageable);

        return ResponseEntity.ok(reviews);
    }
}
