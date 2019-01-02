package com.three.service.category;

import com.three.beans.Book;
import com.three.beans.BookCategory;

import java.util.List;

public interface ICategoryService {
    public int addCategory(BookCategory bookCategory);
    public int deleteCategory(BookCategory bookCategory);
    public List<BookCategory> findAllCategory();
    public BookCategory findOne(BookCategory bookCategory);

//    public int updateCategory(BookCategory bookCategory);
}
