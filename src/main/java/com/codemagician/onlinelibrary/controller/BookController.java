package com.codemagician.onlinelibrary.controller;

import com.codemagician.onlinelibrary.service.BookService;
import com.codemagician.onlinelibrary.service.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/6/18 14:47
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * get all categories
     *
     * @return
     */
    @GetMapping("/categories")
    ResponseEntity<HashMap<String, List<String>>> getAllCategories() {
        List<String> categories = bookService.getAllCategories();
        HashMap<String, List<String>> response = new HashMap<>();
        response.put("categories", categories);

        return ResponseEntity.ok(response);
    }

    /**
     * search by pagination: /api/books?page=?&size=?
     * or return default-size (i.e., 3) books: /api/book
     *
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<BookVO>> getAllBooks(Pageable pageable) {
        Page<BookVO> books = bookService.getAllBooks(pageable);

        return ResponseEntity.ok(books);
    }

    /**
     * search by title or/and category
     * /api/books?title=?&page=?&size=?
     * /api/books?category=?&page=?&size=?
     * /api/books?title=?&category=?&page=?&size=?
     *
     * @param title
     * @param category
     * @param pageable
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<Page<BookVO>> searchBooks(@RequestParam(required = false) String title,
                                                    @RequestParam(required = false) String category,
                                                    Pageable pageable) {
        Page<BookVO> books = bookService.searchBooks(title, category, pageable);

        return ResponseEntity.ok(books);
    }

    /**
     * checkout book
     *
     * /api/books/secure/checkout?bookId=?
     * @param bookId
     * @return
     */
    @PutMapping("/secure/checkout")
    public  ResponseEntity<BookVO> checkoutBook(@RequestParam Long bookId) {
        // @TODO user email should be extracted from JWT
        String userEmail = "testuser@email.com";
        BookVO book = bookService.checkoutBook(userEmail, bookId);

        return ResponseEntity.ok(book);
    }

    /**
     * check the checkout state of the book
     * /api/books/secure/ischeckout/byuser
     * @param bookId
     * @return
     */
    @GetMapping("/secure/ischeckout/byuser")
    public ResponseEntity<Boolean> validateCheckout(@RequestParam Long bookId) {
        // @TODO user email should be extracted from JWT
        String userEmail = "testuser@email.com";
        Boolean isCheckout = bookService.isCheckout(userEmail, bookId);

        return ResponseEntity.ok(isCheckout);
    }


    /**
     * get the number of loans of book for the user
     * /api/books/secure/loans/count
     * @return
     */
    @GetMapping("/secure/loans/count")
    public ResponseEntity<Integer> getCurrentLoans() {
        // @TODO user email should be extracted from JWT
        String userEmail = "testuser@email.com";
        int num = bookService.countCurrentLoans(userEmail);

        return ResponseEntity.ok(num);
    }

}
