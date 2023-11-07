package com.codemagician.onlinelibrary.service.impl;

import com.codemagician.onlinelibrary.dao.repo.HistoryRepository;
import com.codemagician.onlinelibrary.domain.entity.BookDO;
import com.codemagician.onlinelibrary.domain.entity.CheckoutDO;
import com.codemagician.onlinelibrary.dao.repo.BookRepository;
import com.codemagician.onlinelibrary.dao.repo.CheckoutRepository;
import com.codemagician.onlinelibrary.domain.entity.HistoryDO;
import com.codemagician.onlinelibrary.domain.rsp.CurrentLoansRsp;
import com.codemagician.onlinelibrary.common.exception.BusinessException;
import com.codemagician.onlinelibrary.common.exception.NotFoundException;
import com.codemagician.onlinelibrary.service.BookService;
import com.codemagician.onlinelibrary.domain.vo.BookVO;
import com.codemagician.onlinelibrary.util.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CheckoutRepository checkoutRepository;
    @Autowired
    private HistoryRepository historyRepository;

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
    public List<CurrentLoansRsp> currentLoans(Long userId) {

        List<CheckoutDO> checkouts = checkoutRepository.findByUserId(userId);

        List<Long> bookIdList = new ArrayList<>();
        for (CheckoutDO checkout : checkouts) {
            bookIdList.add(checkout.getBookId());
        }
        List<BookDO> books = bookRepository.findAllById(bookIdList);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<CurrentLoansRsp> currentLoansRspList = new ArrayList<>();
        for (BookDO book : books) {
            Optional<CheckoutDO> checkout = checkouts.stream().filter(c -> c.getBookId() == book.getId()).findFirst();
            if (checkout.isPresent()) {
                try {
                    BookVO bookVO = ObjectMapperUtils.map(book, BookVO.class);

                    Date returnDate = sdf.parse(checkout.get().getReturnDate());
                    Date currDate = sdf.parse(LocalDate.now().toString());
                    TimeUnit time = TimeUnit.DAYS;
                    long diffInTime = time.convert(returnDate.getTime() - currDate.getTime(), TimeUnit.MILLISECONDS);

                    currentLoansRspList.add(new CurrentLoansRsp(bookVO, (int) diffInTime));
                } catch (ParseException e) {
                    logger.error("date parsed error while getting loans ", e);
                    throw new RuntimeException(e);
                }
            }
        }

        return currentLoansRspList;
    }

    @Override
    public void returnBook(Long bookId, Long userId) {
        Optional<BookDO> bookOpt = bookRepository.findById(bookId);
        if (!bookOpt.isPresent()) {
            throw new NotFoundException("Book doesn't exist");
        }
        BookDO book = bookOpt.get();

        CheckoutDO checkout = checkoutRepository.findByUserIdAndBookId(userId, bookId);
        if (null == checkout) {
            throw new NotFoundException("Book is not checked out by user");
        }

        // increment book stock by 1
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookRepository.save(book);

        // delete checkout record
        checkoutRepository.deleteById(checkout.getId());

        // add checkout to history
        HistoryDO history = new HistoryDO(userId, checkout.getCheckoutDate(), LocalDate.now().toString(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getImg());
        historyRepository.save(history);
    }

    @Override
    public void renewLoan(Long bookId, Long userId) {
        CheckoutDO checkout = checkoutRepository.findByUserIdAndBookId(userId, bookId);
        if (null == checkout) {
            throw new NotFoundException("Book is not checked out by user");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date returnDate = sdf.parse(checkout.getReturnDate());
            Date now = sdf.parse(LocalDate.now().toString());
            if (returnDate.compareTo(now) > 0 || returnDate.compareTo(now) == 0) {
                // only renew while eligible
                checkout.setReturnDate(LocalDate.now().plusDays(7).toString());
                checkoutRepository.save(checkout);
            }
        } catch (ParseException e) {
            logger.error("date parsed error while renewing loans ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countCurrentLoans(Long userId) {
        return checkoutRepository.countByUserId(userId);
    }

}

