package com.codemagician.onlinelibrary.service.impl;

import com.codemagician.onlinelibrary.domain.entity.ReviewDO;
import com.codemagician.onlinelibrary.dao.repo.ReviewRepository;
import com.codemagician.onlinelibrary.domain.req.LeaveReviewReq;
import com.codemagician.onlinelibrary.domain.rsp.ReviewWithUserInfoRsp;
import com.codemagician.onlinelibrary.service.ReviewService;
import com.codemagician.onlinelibrary.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 15:42
 */

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Page<ReviewWithUserInfoRsp> findByBookId(Long bookId, Pageable pageable) {
        Page<ReviewDO> reviews = reviewRepository.findByBookId(bookId, pageable);

        return ObjectMapperUtils.mapPaginatedEntities(reviews, ReviewWithUserInfoRsp.class);
    }

    @Override
    public Boolean isReviewLeftByUser(Long bookId, Long userId) {
        Integer count = reviewRepository.countByBookIdAndUserId(userId, bookId);

        return count >= 1;
    }

    @Override
    public Boolean leaveReview(LeaveReviewReq req, Long userId) {
        ReviewDO review = new ReviewDO(req.getRating(), req.getReviewDescription(), req.getBookId(), userId);
        reviewRepository.save(review);

        return true;
    }
}
