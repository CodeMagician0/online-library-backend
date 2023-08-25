package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.dao.entity.ReviewDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 15:29
 */
public interface ReviewRepository extends JpaRepository<ReviewDO, Long> {
    @Query("select r from ReviewDO r where r.bookId = ?1")
    Page<ReviewDO> findByBookId(Long bookId, Pageable pageable);

    @Query("select r from ReviewDO r " +
            "where lower(r.userEmail) like lower(:userEmail)" +
            "and r.bookId = :bookId")
    Page<ReviewDO> findByUserEmailAndBookId(String userEmail, Long bookId, Pageable pageable);
}
