package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.dao.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/31 16:18
 */
public interface UserRepository extends JpaRepository<UserDO, Long> {

    Optional<UserDO> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
