package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.domain.entity.RoleDO;
import com.codemagician.onlinelibrary.common.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/31 18:08
 */
public interface RoleRepository extends JpaRepository<RoleDO, Long> {
    Optional<RoleDO> findByName(RoleEnum name);
}
