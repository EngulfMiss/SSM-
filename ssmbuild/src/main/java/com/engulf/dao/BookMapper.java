package com.engulf.dao;

import com.engulf.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    //增加一本书
    int addBook(Books books);
    //删除一本书
    int delBook(@Param("id") int id);
    //修改一本书
    int updateBook(Books books);
    //查询一本书
    Books selectBookById(@Param("id") int id);
    //查询所有书
    List<Books> selectAllBooks();
    //模糊查询
    List<Books> selectLikeBooks(String bookName);
}
