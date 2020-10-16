package com.example.wn.service;

import com.example.wn.dao.BookDAO;
import com.example.wn.pojo.Book;
import com.example.wn.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDAO bookDAO;
    @Autowired
    CategoryService categoryService;
    //获取所有书籍
    public List<Book> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return bookDAO.findAll(sort);
    }
    //增加或更新
    public void addOrUpdate(Book book) {

        bookDAO.save(book);//当主键存在时更新数据，当主键不存在时插入数据。
    }
    //通过id删除
    public void deleteById(int id) {
        bookDAO.deleteById(id);
    }
//    分类查找
    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookDAO.findAllByCategory(category);
    }
}


