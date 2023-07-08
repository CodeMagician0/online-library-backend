package com.codemagician.onlinelibrary.service.impl;

import com.codemagician.onlinelibrary.dao.entity.BookDO;
import com.codemagician.onlinelibrary.dao.repo.BookRepository;
import com.codemagician.onlinelibrary.service.BookService;
import com.codemagician.onlinelibrary.service.dto.BookDTO;
import com.codemagician.onlinelibrary.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> findAllBook() {
        List<BookDO> books = bookRepository.findAll();
        return ObjectMapperUtils.mapAll(books, BookDTO.class);
    }

    public Page<BookDTO> listBook(Pageable pageable) {
        Page<BookDO> books = bookRepository.findAll(pageable);
        return ObjectMapperUtils.mapPaginatedEntities(books, BookDTO.class);
    }
}
