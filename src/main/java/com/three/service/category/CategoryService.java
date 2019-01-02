package com.three.service.category;

import com.three.beans.Book;
import com.three.beans.BookCategory;
import com.three.dao.BookCategoryDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {


    private static Logger logger = LogManager.getLogger(CategoryService.class);
    @Autowired
    BookCategoryDao bookCategoryDao;

    @Override
    public int addCategory(BookCategory bookCategory) {
        BookCategory bookCategory1 = null;
        bookCategory1 = bookCategoryDao.findBookCategoryByName(bookCategory);
        if(bookCategory1 == null){
            bookCategory.setStatus(1);
            return bookCategoryDao.addBookCategoty(bookCategory);
        }
        return 0;
    }

//    @Override
//    public int updateCategory(BookCategory bookCategory) {
//        return bookCategoryDao.updateBookInfo(bookCategory);
//    }

    @Override
    public int deleteCategory(BookCategory bookCategory) {


        BookCategory bookCategory1 = bookCategoryDao.findBookCategoryByName(bookCategory);
        logger.info("delete catefory: "+bookCategory1.getCategory());
        logger.info("delete category Servie :"+bookCategory1.getId());

        return bookCategoryDao.deleteBookCategoty(bookCategory1);

    }

    @Override
    public List<BookCategory> findAllCategory() {
        List<BookCategory> list = null;
        list =  bookCategoryDao.findAllCategory();
        if(list != null)
            return list;
        return null;
    }

    @Override
    public BookCategory findOne(BookCategory bookCategory) {
        return bookCategoryDao.findBookCategoryByName(bookCategory);
    }


////没有测试完成
//    @Override
//    public List<Book> getBoosByCategory(BookCategory category) {
//        category =  bookCategoryDao.findBookCategoryByName(category);
//        return category.getBooks();
//    }


}
