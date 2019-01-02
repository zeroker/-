package com.three.service.book;

import com.three.beans.Book;
import com.three.beans.BookCategory;
import com.three.beans.OrderItem;
import com.three.beans.User;
import com.three.service.user.IUserService;
import com.three.service.user.UserService;
import com.three.utils.HibernateDao;
import com.three.utils.other.UUIDUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    private static Logger logger = LogManager.getLogger(User.class);
    private SessionFactory sessionFactory;
    ApplicationContext ctx;
    IBookService bookService;
    HibernateDao<OrderItem> orderItemHibernateDao;
    @Before
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
        bookService = (IBookService) ctx.getBean("bookService");
        orderItemHibernateDao = (HibernateDao<OrderItem>) ctx.getBean("hibernateDao");
    }





    @Test
    public void insertBook(){

        List<Book> bookList = new ArrayList<Book>();
        for(int i=0; i<5; i++){
            Book book = new Book();
            book.setAuthor("au:"+i);
            book.setName("name+"+i);
            book.setB_id("1ADB2FFD684F490E9A49D50E761E4B99");
            bookService.addBook(book);
        }

        logger.info("完成！");

    }


    @Test
    public void addBook() {
        Book book = new Book("java001","李白",10,1000,"","1.1");
        book.setB_id("1");  //java分类


        Assert.assertTrue(bookService.addBook(book) == 1);
    }

    @Test
    public void deleteBook() {
        Book book = new Book();
        book.setName("java分类的");
        Assert.assertTrue(bookService.deleteBook(book) == 1);
    }

    @Test
    public void updateBook() {
        Book book = new Book();
        book.setName("java分类的");
        book = bookService.findByBookName(book);
        book.setNum(10222);
        book.setPrice(1500);
        Assert.assertTrue(bookService.updateBook(book) == 1);
    }

    @Test
    public void findByBookName() {
        Book book = new Book();
        book.setName("java分类的");
        book = bookService.findByBookName(book);
        logger.info(book.getId());

    }

    @Test
    public void findByName() {

        //书和订单详情是一对一的关系
        Book book = new Book();
        book.setName("7`7`77");
        book = bookService.findByBookName(book);

        System.out.println(book);
        System.out.println(book.getAuthor());
        BookCategory bookCategory = book.getBookCategory();
        logger.info(book.getB_id());

        //把书放到订单里面

        OrderItem orderItem = new OrderItem();
        orderItem.setBook(book);
        orderItem.setCount(5);
        orderItem.setSubtotal(50);
        orderItem.setItemId(1);                          //订单号
        orderItem.setbId(book.getId());

        orderItemHibernateDao.insert(orderItem);


        logger.info(bookCategory.getCategory());


    }

    //0
    @Test
    public void findByCategory() {
        BookCategory category = new BookCategory();
        category.setCategory("数学");
        List<Book> bookList  = bookService.findByCategory(category);

        for(Book book:bookList)
            System.out.println(book.getAuthor());
    }

    @Test
    public void findByProName() {
        Book book = new Book();
        book.setAuthor("1");
        book.setName("唐");
        List<Book> bookList = null;
        bookList = bookService.findByBookProName(book);

        logger.info("size: "+bookList.size());

        for(Book book1:bookList)
            System.out.println(book1.getName());

    }

    @Test
    public void findByPro() {
        Book book = new Book();
//        book.setAuthor("1");

        book.setName("唐");

        ArrayList<Book> list = (ArrayList<Book>) bookService.findByPro(book);
        for(Book book1:list)
            System.out.println(book1.getName());


    }


    @Test
    public void findAll() {

        List<Book> bookList  = bookService.findAllBooks();

        for(Book book:bookList)
            System.out.println(book.getAuthor());
    }


    @After
    public void tearDown() throws Exception {
    }
}