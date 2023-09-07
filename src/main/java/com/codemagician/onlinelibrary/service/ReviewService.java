package com.codemagician.onlinelibrary.service;

import com.codemagician.onlinelibrary.domain.rsp.ReviewWithUserInfoRsp;
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
    Page<ReviewWithUserInfoRsp> findByBookId(Long bookId, Pageable pageable);
}
