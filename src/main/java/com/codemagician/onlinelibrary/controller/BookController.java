package com.codemagician.onlinelibrary.controller;

import com.codemagician.onlinelibrary.service.BookService;
import com.codemagician.onlinelibrary.service.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
