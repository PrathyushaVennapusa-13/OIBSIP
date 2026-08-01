package com.lms.dto;

import com.lms.util.BookCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    private Integer bookId;
    private String title;
    private String author;
    private String isbn;
    private BookCategory category;
    private Integer totalQuantity;
    private Integer availableQuantity;

}