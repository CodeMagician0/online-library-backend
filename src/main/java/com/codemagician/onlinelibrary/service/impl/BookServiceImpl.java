package com.codemagician.onlinelibrary.service.impl;

import com.codemagician.onlinelibrary.dao.entity.BookDO;
import com.codemagician.onlinelibrary.dao.repo.BookRepository;
import com.codemagician.onlinelibrary.service.BookService;
import com.codemagician.onlinelibrary.service.vo.BookVO;
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

    @Override
    public List<String> getAllCategories() {
        return bookRepository.getAllCategories();
    }

    @Override
    public Page<BookVO> getAllBooks(Pageable pageable) {
        Page<BookDO> books = bookRepository.findAll(pageable);
        return ObjectMapperUtils.mapPaginatedEntities(books, BookVO.class);
    }

    @Override
    public Page<BookVO> searchBooks(String title, String category, Pageable pageable) {
        Page<BookDO> books = bookRepository.searchBooks(title, category, pageable);
        return ObjectMapperUtils.mapPaginatedEntities(books, BookVO.class);
    }
}
