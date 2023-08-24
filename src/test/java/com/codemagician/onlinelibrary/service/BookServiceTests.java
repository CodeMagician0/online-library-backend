package com.codemagician.onlinelibrary.service;

import com.codemagician.onlinelibrary.dao.entity.BookDO;
import com.codemagician.onlinelibrary.dao.repo.BookRepository;
import com.codemagician.onlinelibrary.service.dto.BookDTO;
import com.codemagician.onlinelibrary.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Bingqi Xia
 * @version 1.0
 * @date 23/08/2023 20:51
 */

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void getAllCategories_ReturnsAPagedListOfCategories() {
        List<String> sampleCategories = Arrays.asList("FE", "Data", "BE");
        when(bookRepository.getAllCategories()).thenReturn(sampleCategories);

        List<String> categories = bookService.getAllCategories();
        assertThat(categories.size(), equalTo(sampleCategories.size()));

        verify(bookRepository).getAllCategories();
    }

    @Test
    void getAllBooks_ReturnsAPagedListOfBooks() {

        List<BookDO> mockBookList = new ArrayList<>();
        mockBookList.add(new BookDO());
        mockBookList.add(new BookDO());
        Page<BookDO> mockPage = new PageImpl<>(mockBookList);

        Pageable pageable = PageRequest.of(0, 9); // Example pageable
        when(bookRepository.findAll(pageable)).thenReturn(mockPage);

        Pageable pageRequest = PageRequest.of(0, 9);
        List<BookDTO> books = bookService.getAllBooks(pageRequest).getContent();

        assertThat(books.size(), equalTo(2));

        verify(bookRepository).findAll(pageRequest);
    }

    @Test
    void testSearchBooks() {

    }
}
