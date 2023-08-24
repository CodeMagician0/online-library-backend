package com.codemagician.onlinelibrary.dao.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @author Bingqi Xia
 * @version 1.0
 * @date 23/08/2023 21:07
 */

@DataJpaTest
public class BookRepositoryTests {
    @Autowired
    private BookRepository bookRepository;

    // need a mock mysql database to test


}
