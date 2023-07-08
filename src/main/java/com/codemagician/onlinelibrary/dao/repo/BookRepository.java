package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.dao.entity.BookDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/6/18 12:45
 */
public interface BookRepository extends JpaRepository<BookDO, Long> {
}
