package com.three.dao;

import com.three.beans.BookCategory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.three.utils.HibernateDao;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookCategoryDao extends HibernateDao<BookCategory> {

    private  static Logger logger = LogManager.getLogger(BookCategory.class);




    //添加类别
    public int addBookCategoty(BookCategory bookCategory) {
        return this.insert(bookCategory);
    }



//    假删除类别
    public int deleteBookCategoty(BookCategory bookCategory){
        BookCategory bookCategory1 = findBookCategoryByName(bookCategory);
        bookCategory1.setStatus(0);
        return this.update(bookCategory1);
    }

////    更改分类的名字
//    public int updateBookInfo(BookCategory bookCategory,String newName){
//        BookCategory bookCategory1 = findBookCategoryByName(bookCategory);
//        bookCategory1.setCategory(newName);
//        return this.update(bookCategory1);
//    }



//    根据分类准确查询（唯一性）
    public BookCategory findBookCategoryByName(BookCategory bookCategory){

        logger.info("bookcategory:"+bookCategory);
        logger.info("category: " + bookCategory.getCategory());

        String hql = "select categoryName from BookCategory categoryName where category = ? and status = 1";
        String pram[] = {bookCategory.getCategory()};
        return this.findOne(hql,pram);

    }


    public List<BookCategory> findAllCategory(){

        String param[] = new String[0];
        String hql = "From BookCategory where status = 1";
        List<BookCategory> list = null;
        list =  find(hql,param);
        if(list != null)
            return list;
        return null;
    }



}
