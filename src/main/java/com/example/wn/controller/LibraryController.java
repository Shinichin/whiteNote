package com.example.wn.controller;

import com.example.wn.pojo.Book;
import com.example.wn.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;
//获取所有书籍
    @GetMapping("/api/books")
    public List<Book> list() throws Exception {
        return bookService.list();
    }
//更新或增加书籍
    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception {
        bookService.addOrUpdate(book);
        return book;
    }
//删除
    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception {
        bookService.deleteById(book.getId());
    }

//分类查找
    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (0 != cid) {
            return bookService.listByCategory(cid);
        } else {
            return list();
        }
    }
}


