package com.codemagician.onlinelibrary.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/6/18 12:12
 */

@Entity
@Table(name = "book")
@Data
public class BookDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "copies")
    private int copies;

    @Column(name = "copies_available")
    private int copiesAvailable;

    @Column(name = "category")
    private String category;

    @Column(name = "img")
    private String img;
}
