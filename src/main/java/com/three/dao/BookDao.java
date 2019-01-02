package com.three.dao;

import com.three.beans.Book;
import com.three.beans.BookCategory;
import com.three.beans.User;
import com.three.utils.HibernateDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao extends HibernateDao<Book> {

    private  static Logger logger = LogManager.getLogger(UserDao.class);


    @Autowired
    BookCategoryDao bookCategoryDao;


    //添加书籍
    public int addBook(Book book) {
        return this.insert(book);
    }


    //假删除书籍(根据书名)
    public int deleteBook(Book book){
        Book book1 = findBookByName(book);
        book1.setStatus(0);

        return this.update(book1);
    }

    //更改书籍信息(更改价格！更改数量)（根据书名）
    public int updateBookInfo(Book book){
        Book book1 = findBookByName(book);
        book1.setPrice(book.getPrice());
        book1.setNum(book.getNum());

        return this.update(book1);
    }

    //查询所有的 (书名)
    public List<Book> findAllBooks(){
        String hql = "from Book";
        List<Book> list  = this.find(hql,null);
        if(list == null)
            return null;
        else
            return list;
    }


    //模糊查询书名 (书名)
    public List<Book> findBookByProName(Book book){
        String hql = "from Book where name like '%"+book.getName()+"%'";
        List<Book> list  = this.find(hql,null);
        if(list == null)
            return null;
        else
            return list;
    }

    //根据书名准确查询（唯一性）
    public Book findBookByName(Book book){
        String hql = "select book from Book book where name = ? and status = 1";
        String pram[] = {book.getName()};
        return this.findOne(hql,pram);

    }


//    分类查询
    public List<Book> findBooksByCategory(BookCategory category){
        logger.info("bookdao fenleichaxun");
        BookCategory category_temp = bookCategoryDao.findBookCategoryByName(category);
        return category_temp.getBooks();

    }


    //模糊查询
    public List<Book> findBooksByPro(String []filds,String []value){
        String hql = "from Book ";
        return this.findByFields(hql,filds,value);
    }

}
