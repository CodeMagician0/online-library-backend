package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.domain.entity.ReviewDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 15:29
 */
public interface ReviewRepository extends JpaRepository<ReviewDO, Long> {

    // use combination of NamedEntityGraph and EntityGraph to avoid N+1 problem
    @EntityGraph(value = "Review.user", type = EntityGraph.EntityGraphType.FETCH)
    Page<ReviewDO> findByBookId(Long bookId, Pageable pageable);

    @EntityGraph(value = "Review.user", type = EntityGraph.EntityGraphType.FETCH)
    Page<ReviewDO> findByUserIdAndBookId(Long userId, Long bookId, Pageable pageable);

    ReviewDO countByUserIdAndBookId(Long userId, Long bookId);
}
