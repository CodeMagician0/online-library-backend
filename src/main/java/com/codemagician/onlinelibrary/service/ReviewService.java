package com.codemagician.onlinelibrary.service;

import com.codemagician.onlinelibrary.domain.vo.ReviewVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 15:34
 */
public interface ReviewService {

    /**
     * find reviews of the book with bookId
     * @param bookId
     * @param pageable
     * @return
     */
    Page<ReviewVO> findByBookId(Long bookId, Pageable pageable);

    /**
     * find reviews by user email and bookId
     * @param userEmail
     * @param bookId
     * @return
     */
    Page<ReviewVO> findByUserEmailAndBookId(String userEmail, Long bookId, Pageable pageable);
}
