package com.engulf.service;

import com.engulf.dao.BookMapper;
import com.engulf.pojo.Books;

import java.util.List;

public class BookServiceImpl implements BookService {

    //service 到用 dao层，组合Dao层
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int addBook(Books books) {
        return bookMapper.addBook(books);
    }

    public int delBook(int id) {
        return bookMapper.delBook(id);
    }

    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }

    public Books selectBookById(int id) {
        return bookMapper.selectBookById(id);
    }

    public List<Books> selectAllBooks() {
        return bookMapper.selectAllBooks();
    }

    public List<Books> selectLikeBooks(String bookName) {
        return bookMapper.selectLikeBooks(bookName);
    }
}
