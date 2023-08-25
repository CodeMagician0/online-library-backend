package com.codemagician.onlinelibrary.service.impl;

import com.codemagician.onlinelibrary.dao.entity.ReviewDO;
import com.codemagician.onlinelibrary.dao.repo.ReviewRepository;
import com.codemagician.onlinelibrary.service.ReviewService;
import com.codemagician.onlinelibrary.service.vo.ReviewVO;
import com.codemagician.onlinelibrary.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 15:42
 */

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Page<ReviewVO> findByBookId(Long bookId, Pageable pageable) {
        Page<ReviewDO> reviews = reviewRepository.findByBookId(bookId, pageable);

        return ObjectMapperUtils.mapPaginatedEntities(reviews, ReviewVO.class);
    }

    @Override
    public Page<ReviewVO> findByUserEmailAndBookId(String userEmail, Long bookId, Pageable pageable) {
        Page<ReviewDO> reviews = reviewRepository.findByUserEmailAndBookId(userEmail, bookId, pageable);

        return ObjectMapperUtils.mapPaginatedEntities(reviews, ReviewVO.class);
    }
}
