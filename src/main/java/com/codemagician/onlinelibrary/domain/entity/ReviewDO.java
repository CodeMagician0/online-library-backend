package com.codemagician.onlinelibrary.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;



/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/24 15:24
 */

@Entity
@Table(name = "review")
@Data
@NamedEntityGraph(name = "Review.user", attributeNodes = @NamedAttributeNode("user"))
public class ReviewDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
//    @Column(name = "user_id")
//    private String userId;

    @Column(name = "date")
    @CreationTimestamp
    private Date date;

    @Column(name = "rating")
    private double rating;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "review_description")
    private String reviewDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserDO user;

}
