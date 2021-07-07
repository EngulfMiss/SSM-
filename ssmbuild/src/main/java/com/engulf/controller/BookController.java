package com.engulf.controller;

import com.engulf.pojo.Books;
import com.engulf.service.BookService;
import com.engulf.service.BookServiceImpl;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    //controller调用service层
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    //查询所有数据，返回到一个页面上
    @RequestMapping("/allBook")
    public String bookList(Model model){
        List<Books> books = bookService.selectAllBooks();
        model.addAttribute("booklist",books);
        return "allBook";
    }


    //跳转到添加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddBookPage(){
        return "addPage";
    }

    //提交添加的书籍
    @RequestMapping("/addBook")
    public String addBook(Books books){
        System.out.println("添加书籍-->" + books);
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    //跳转更新书籍页面
    @RequestMapping("/toUpdateBook/{bookID}")
    public String toUpdatePage(@PathVariable("bookID") int id, Model model){
        Books books = bookService.selectBookById(id);
        model.addAttribute("SBook",books);
        bookService.updateBook(books);
        return "updatePage";
    }

    //更新书籍信息
    @RequestMapping("/updateBook")
    public String updateBook(Books books){
        System.out.println("修改书籍-->" + books);
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }

    //删除书籍
    @RequestMapping("/delBook")
    public String deleteBook(@RequestParam("bookId") int id){
        bookService.delBook(id);
        return "redirect:/book/allBook";
    }

    //查询书籍
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName,Model model){
        List<Books> books = bookService.selectLikeBooks(queryBookName);
        model.addAttribute("booklist",books);
        return "allBook";
    }
}
