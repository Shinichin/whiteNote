package com.example.wn.dao;

import com.example.wn.pojo.Book;
import com.example.wn.pojo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDAO extends JpaRepository<Book,Integer> {
    List<Book> findAllByCategory(Category category);//category 对象的 id 属性作为 cid
    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
}

