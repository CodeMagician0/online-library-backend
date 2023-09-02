package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.domain.entity.CheckoutDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/25 18:08
 */
public interface CheckoutRepository extends JpaRepository<CheckoutDO, Long> {
    CheckoutDO findByUsernameAndBookId(String username, Long bookId);
    int countByUsername(String username);
}
