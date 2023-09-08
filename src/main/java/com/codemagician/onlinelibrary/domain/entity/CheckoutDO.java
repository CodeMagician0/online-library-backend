package com.codemagician.onlinelibrary.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/25 18:05
 */
@Entity
@Table(name = "checkout")
@Data
@NoArgsConstructor
public class CheckoutDO {

    public CheckoutDO(Long userId, String checkoutDate, String returnDate, Long bookId) {
        this.user = new UserDO(userId);
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.bookId = bookId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "checkout_date")
    private String checkoutDate;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "book_id")
    private Long bookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserDO user;
}
