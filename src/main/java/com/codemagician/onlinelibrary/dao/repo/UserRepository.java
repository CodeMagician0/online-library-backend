package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.domain.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/31 16:18
 */
public interface UserRepository extends JpaRepository<UserDO, Long> {

    // use JOIN FETCH to resolve N+1 problem
    @Query("select u from UserDO u JOIN FETCH u.roles where u.username = :username")
    Optional<UserDO> findByUsernameWithRoles(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
