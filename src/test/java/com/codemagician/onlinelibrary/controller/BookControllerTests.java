package com.codemagician.onlinelibrary.controller;

import com.codemagician.onlinelibrary.service.BookService;
import com.codemagician.onlinelibrary.domain.vo.BookVO;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Bingqi Xia
 * @version 1.0
 * @date 24/08/2023 17:15
 */

@WebMvcTest(BookController.class)
public class BookControllerTests {
    @MockBean
    private BookService bookService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllCategories() throws Exception {
        List<String> categories = Arrays.asList("FE", "Data", "BE");
        Map<String, List<String>> map = new HashMap<>();
        map.put("categories", categories);
        Gson gson = new Gson();
        String jsonString = gson.toJson(map);

        when(bookService.getAllCategories()).thenReturn(categories);
        mockMvc.perform(get("/books/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonString));
    }

    @Test
    void testGetAllBooks() throws Exception {
        List<BookVO> mockBookList = new ArrayList<>();
        mockBookList.add(new BookVO());
        mockBookList.add(new BookVO());
        Page<BookVO> mockPage = new PageImpl<>(mockBookList);

        Pageable pageable = PageRequest.of(0, 9); // Example pageable
        when(bookService.getAllBooks(pageable)).thenReturn(mockPage);

        mockMvc.perform(get("/books")
                        .param("page", "0")
                        .param("size", "9")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(mockBookList.size()));
        verify(bookService).getAllBooks(pageable);
    }

    @Test
    void testSearchBooks() throws Exception {

    }

}
