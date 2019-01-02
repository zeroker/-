package com.three.dao;

import com.three.beans.Book;
import com.three.beans.BookCategory;
import com.three.beans.User;
import com.three.service.book.IBookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class BookCategoryDaoTest {



    private static Logger logger = LogManager.getLogger(User.class);
    private SessionFactory sessionFactory;
    ApplicationContext ctx;
    BookCategoryDao bookCategoryDao;
    @Before
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
        bookCategoryDao = (BookCategoryDao) ctx.getBean("bookCategoryDao");
    }




    @Test
    public void findBookCategoryByName() {
//       BookCategoryDao bookCategoryDao = new BookCategoryDao();
        BookCategory category = new BookCategory();
        category.setCategory("java");
        BookCategory category1 = bookCategoryDao.findBookCategoryByName(category);

        System.out.println(category1.getC_id());

    }
}