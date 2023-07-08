package com.codemagician.onlinelibrary.service.dto;

import lombok.Data;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/6/18 14:21
 */
@Data
public class BookDTO {

    private Long id;

    private String title;

    private String author;

    private String description;

    private int copies;

    private int copiesAvailable;

    private String category;

    private String img;
}
