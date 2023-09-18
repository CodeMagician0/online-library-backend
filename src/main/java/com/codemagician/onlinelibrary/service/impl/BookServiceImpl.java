package com.codemagician.onlinelibrary.service.impl;

import com.codemagician.onlinelibrary.domain.entity.BookDO;
import com.codemagician.onlinelibrary.domain.entity.CheckoutDO;
import com.codemagician.onlinelibrary.dao.repo.BookRepository;
import com.codemagician.onlinelibrary.dao.repo.CheckoutRepository;
import com.codemagician.onlinelibrary.exception.BusinessException;
import com.codemagician.onlinelibrary.exception.NotFoundException;
import com.codemagician.onlinelibrary.service.BookService;
import com.codemagician.onlinelibrary.domain.vo.BookVO;
import com.codemagician.onlinelibrary.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CheckoutRepository checkoutRepository;

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
    public BookVO getBookInfo(Long bookId) {
        Optional<BookDO> bookOpt = bookRepository.findById(bookId);

        if (!bookOpt.isPresent()) {
            throw new NotFoundException("Book doesn't exist");
        }

        BookDO book = bookOpt.get();

        return ObjectMapperUtils.map(book, BookVO.class);
    }

    @Override
    public Page<BookVO> searchBooks(String title, String category, Pageable pageable) {
        Page<BookDO> books = bookRepository.searchBooks(title, category, pageable);
        return ObjectMapperUtils.mapPaginatedEntities(books, BookVO.class);
    }

    @Override
    public BookVO checkoutBook(Long userId, Long bookId) {
        Optional<BookDO> bookOpt = bookRepository.findById(bookId);
        if (!bookOpt.isPresent()) {
            throw new NotFoundException("Book doesn't exist");
        }

        if (isCheckout(userId, bookId)) {
            throw new BusinessException("Book already checked out by user");
        }

        BookDO book = bookOpt.get();
        if (book.getCopiesAvailable() <= 0) {
            throw new BusinessException("Temporary unavailable due to not enough copies");
        }

        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        bookRepository.save(book);

        CheckoutDO newCheckout = new CheckoutDO(userId, LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(), book.getId());

        checkoutRepository.save(newCheckout);

        return ObjectMapperUtils.map(book, BookVO.class);
    }

    @Override
    public Boolean isCheckout(Long userId, Long bookId) {
        return checkoutRepository.existsByUserIdAndBookId(userId, bookId);
    }

    @Override
    public int countCurrentLoans(Long userId) {
        return checkoutRepository.countByUserId(userId);
    }

}

