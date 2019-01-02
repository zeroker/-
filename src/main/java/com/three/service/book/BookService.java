package com.three.service.book;

import com.three.beans.Book;
import com.three.beans.BookCategory;
import com.three.dao.BookDao;
import com.three.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class BookService implements IBookService {

    private Logger logger = LogManager.getLogger(UserService.class);
    @Resource
    private BookDao bookDao;
//    @Resource
//    private BookCategory category;

    public BookDao getBookDao() {
        return bookDao;
    }
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public int addBook(Book book) {

        Book book1 = bookDao.findBookByName(book);
        if(book1 == null){
            book.setStatus(1);
            return bookDao.addBook(book);
        }

        return 0;
    }

    @Override
    public int deleteBook(Book book) {
        return bookDao.deleteBook(book);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBookInfo(book);
    }


    public List<Book> findAllBooks(){
        return bookDao.findAllBooks();
    }

    @Override
    public Book findByBookName(Book book) {
        return bookDao.findBookByName(book);
    }

    //根据书名模糊查询
    @Override
    public List<Book> findByBookProName(Book book) {
        return bookDao.findBookByProName(book);
    }


    //分类查询
    @Override
    public List<Book> findByCategory(BookCategory category) {
        logger.info("book service fenlei chaxun");
        return bookDao.findBooksByCategory(category);
    }

    //暂定！！！！  ok!
    @Override
    public List<Book> findByPro(Book book) {


        String name = book.getName();
        String author = book.getAuthor();
        logger.info(name);
        logger.info(author);

        int id = 0;
        int num = 0;
        if(name !=null && !name.equals("")) {
            num++;
            logger.info("name != null");
        }
        if(author!=null && !author.equals("")) {
            logger.info("author != null");
            num++;
        }

        logger.info(num);


        String [] filds = new String[num];
        String [] values = new String[num];

        if(name != null && !name.equals("")){
            logger.info(name);
            filds[id] = "name";
            values[id] = name;
            id++;
            logger.info("id"+id);

        }
        logger.info("id"+id);
        if(author!=null && !author.equals("")){
            logger.info(author);
            filds[id] = "author";
            values[id] = author;
            id++;
        }

        logger.info(filds.length);

        return bookDao.findBooksByPro(filds,values);

    }
}
