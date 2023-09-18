package com.codemagician.onlinelibrary.service;

import com.codemagician.onlinelibrary.domain.vo.BookVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/6/18 13:20
 */
public interface BookService {

    /**
     * Retrieve distinct categories
     * @return
     */
    List<String> getAllCategories();

    /**
     * retrieve all books
     * @param pageable
     * @return
     */
    Page<BookVO> getAllBooks(Pageable pageable);

    /**
     * search books by title or/and category
     * @param title
     * @param category
     * @param pageable
     * @return
     */
    Page<BookVO> searchBooks(String title, String category, Pageable pageable);

    /**
     * checkout book throw NotFoundException
     * @param userId
     * @param bookId
     * @return
     */
    BookVO checkoutBook(Long userId, Long bookId);

    /**
     * get book info by bookId
     * @param bookId
     * @return
     */
    BookVO getBookInfo(Long bookId);

    /**
     * validate the checkout state of the book with bookId
     * @param userId
     * @param bookId
     * @return
     */
    Boolean isCheckout(Long userId, Long bookId);

    /**
     * get the number of current loans for user
     * @param userId
     * @return
     */
    int countCurrentLoans(Long userId);
}
