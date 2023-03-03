package com.test.controller;

import com.test.Service.BookService;
import com.test.entity.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BookController {
    @Resource
    BookService bookService;
    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid)
    {
        return bookService.getBookById(bid);
    }
}
