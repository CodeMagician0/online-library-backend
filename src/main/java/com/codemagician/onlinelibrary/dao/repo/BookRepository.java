package com.codemagician.onlinelibrary.dao.repo;

import com.codemagician.onlinelibrary.dao.entity.BookDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/6/18 12:45
 */
public interface BookRepository extends JpaRepository<BookDO, Long> {

    @Query("select distinct category FROM BookDO")
    List<String> getAllCategories();

    @Query("select book FROM BookDO book " +
            "where (:title is null or lower(book.title) like lower(concat('%', :title, '%')))" +
            "and (:category is null or lower(book.category) = lower(:category))")
    Page<BookDO> searchBooks(String title, String category, Pageable pageable);
}
