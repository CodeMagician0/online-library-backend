package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.dao.entity.BookDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/6/18 12:45
 */
public interface BookRepository extends JpaRepository<BookDO, Long> {

    @Query("select distinct category FROM BookDO")
    List<String> getAllCategories();

    Page<BookDO> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<BookDO> findByCategoryIgnoreCase(String category, Pageable pageable);
    Page<BookDO> findByTitleContainingIgnoreCaseAndCategoryIgnoreCase
            (String title, String category, Pageable pageable);
}
