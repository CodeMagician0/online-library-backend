package com.codemagician.onlinelibrary.controller;

import com.codemagician.onlinelibrary.service.BookService;
import com.codemagician.onlinelibrary.service.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/6/18 14:47
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * search by pagination: /api/book?page=?&size=?
     * or return default-size (i.e., 3) books: /api/book
     * @param pageable
     * @return
     */
    @GetMapping
    public Page<BookDTO> listBook(Pageable pageable) {
        return bookService.listBook(pageable);
    }
}
