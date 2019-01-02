package com.three.service.book;

import com.three.beans.Book;
import com.three.beans.BookCategory;

import java.util.List;

public interface IBookService {
    public int addBook(Book book);
    public int deleteBook(Book book);
    public int updateBook(Book book); //更改 价格

    public List<Book> findAllBooks();
    public Book findByBookName(Book book);
    public List<Book> findByCategory(BookCategory category);        //类别
    public List<Book> findByBookProName(Book book);
    public List<Book> findByPro(Book book);  //模糊

}
