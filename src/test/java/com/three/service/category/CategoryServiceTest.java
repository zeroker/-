package com.three.service.category;

import com.three.beans.Book;
import com.three.beans.BookCategory;
import com.three.beans.User;
import com.three.utils.HibernateDao;
import com.three.utils.other.UUIDUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.plugin.javascript.navig.Array;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceTest {

    private static Logger logger = LogManager.getLogger(User.class);
    private SessionFactory sessionFactory;
    ApplicationContext ctx;
    ICategoryService categoryService;
    @Before
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
        categoryService = (ICategoryService) ctx.getBean("categoryService");
    }

//
//
//    @Test
//    public void test(){
//
//                Configuration cfg = new Configuration().configure();
//        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .applySettings(cfg.getProperties()).build();
//        SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
//        Session session = sessionFactory.openSession();
//
//
//        Transaction transaction = session.beginTransaction();
//
//        String c_id = UUIDUtils.getId();
//
//        BookCategory category = new BookCategory();
//        category.setCategory("hiberbate");
//        category.setC_id(c_id);
//
//        List<Book> list = new ArrayList<Book>();
//
//        for(int j=0;j<10;j++){
//            Book book = new Book();
//            book.setAuthor("author:"+j);
//            book.setName("name is+"+j);
//            book.setB_id(c_id);
//            //注意：
//            //这里设置的值和上面cid的值保持一致
//            //因为cid和orderNumber是关联的字段，而且这两个字段不是主键不能自增
//            //如果这两个关联的字段都是自增的，就可以不设置值
//            list.add(book);
//        }
//
//        category.setBooks(list);
//        logger.info("size:"+list.size());
//
//
//            try {
//                session.save(category);
////                for(int i=0; i<list.size(); i++)
////                    session.save(list.get(i));
//                System.out.println("statics插入成功"+"****************");
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("statics插入失敗");
//            }
//
//
//
//        transaction.commit();
//        logger.info("成功插入");
//        System.out.println("ending!!!!!!");
//
//    }



    //One
    @Test
    public void insertCategory(){

        String c_id = UUIDUtils.getId();
        BookCategory category = new BookCategory();
        category.setStatus(1);
        category.setCategory("java ee");

        category.setC_id(c_id);

        List<Book> bookList = new ArrayList<Book>();
        for(int i=0; i<5; i++){
            Book book = new Book();
            book.setAuthor("author:"+i);
            book.setName("name is+"+i);
            book.setB_id(c_id);
            book.setStatus(1);
//            book.setId(i+1);
            bookList.add(book);
        }

        category.setBooks(bookList);

        categoryService.addCategory(category);

        logger.info("完成！");

    }


///////////////////////////////////////////////////////////////////////////////////////////////////
    //Two
    @Test
    public void findBookByCategory(){


        BookCategory category = new BookCategory();
        category.setCategory("java ee");
        category = categoryService.findOne(category);
        List<Book> books = category.getBooks();

        System.out.println(books.size());
        for(Book book:books)
            System.out.println(book.getName());
        logger.info("ok");

    }




    @Test
    public void addCategory() {
        String c_id = UUIDUtils.getId();
        BookCategory bookCategory = new BookCategory();
        bookCategory.setCategory("java ee");
        bookCategory.setC_id(c_id);
        Assert.assertTrue(categoryService.addCategory(bookCategory) == 1);

    }

    @Test
    public void deleteCategory() {
        BookCategory bookCategory = new BookCategory("java");
        Assert.assertTrue(categoryService.deleteCategory(bookCategory) == 1);
    }


    @Test
    public void findAll(){
        ArrayList<BookCategory> list = (ArrayList<BookCategory>) categoryService.findAllCategory();
        for(BookCategory bookCategory:list)
            System.out.println(bookCategory.getCategory());
    }


    @Test
    public void findOne(){
        BookCategory category = new BookCategory();
        category.setCategory("java");
        category = categoryService.findOne(category);

        System.out.println(category.getC_id());

    }

    @After
    public void tearDown() throws Exception {
    }

}