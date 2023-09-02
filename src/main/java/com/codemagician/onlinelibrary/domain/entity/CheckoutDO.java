package com.codemagician.onlinelibrary.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    public CheckoutDO(String username, String checkoutDate, String returnDate, Long bookId) {
        this.username = username;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.bookId = bookId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String username;

    @Column(name = "checkout_date")
    private String checkoutDate;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "book_id")
    private Long bookId;
}
