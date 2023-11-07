package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.domain.entity.CheckoutDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/25 18:08
 */
public interface CheckoutRepository extends JpaRepository<CheckoutDO, Long> {
    CheckoutDO findByUserIdAndBookId(Long userId, Long bookId);

    Boolean existsByUserIdAndBookId(Long userId, Long bookId);

    Integer countByUserId(Long userId);

    List<CheckoutDO> findByUserId(Long userId);
}
