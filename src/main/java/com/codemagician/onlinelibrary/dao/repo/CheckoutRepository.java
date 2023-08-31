package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.dao.entity.CheckoutDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/25 18:08
 */
public interface CheckoutRepository extends JpaRepository<CheckoutDO, Long> {
    CheckoutDO findByUserEmailAndBookId(String userEmail, Long bookId);
    int countByUserEmail(String userEmail);
}
