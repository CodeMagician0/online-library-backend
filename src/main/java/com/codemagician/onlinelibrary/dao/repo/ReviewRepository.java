package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.domain.entity.ReviewDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 15:29
 */
public interface ReviewRepository extends JpaRepository<ReviewDO, Long> {

    Page<ReviewDO> findByBookId(Long bookId, Pageable pageable);

    Page<ReviewDO> findByUsernameAndBookId(String username, Long bookId, Pageable pageable);
}
