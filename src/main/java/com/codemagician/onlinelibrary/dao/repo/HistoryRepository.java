package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.domain.entity.HistoryDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/10/9 14:07
 */

public interface HistoryRepository  extends JpaRepository<HistoryDO, Long> {
}
