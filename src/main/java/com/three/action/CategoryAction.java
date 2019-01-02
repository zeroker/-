package com.three.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.three.beans.BookCategory;
import com.three.service.category.CategoryService;
import com.three.utils.other.UUIDUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
@Namespace("/category")
@Scope(value = "prototype" )
@ParentPackage(value = "struts-default")
public class CategoryAction extends ActionSupport {

    @Autowired
    private CategoryService categoryService;

    private BookCategory bookCategory;
    private ArrayList<BookCategory> list;

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public ArrayList<BookCategory> getList() {
        return list;
    }

    public void setList(ArrayList<BookCategory> list) {
        this.list = list;
    }

    //增加分类
    @Action(value = "addCategory",
            results = {
                    @Result(name = "success",location = "/success.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String addCategory(){
        String c_id = UUIDUtils.getId();
        bookCategory.setC_id(c_id);
        if (categoryService.addCategory(bookCategory) == 1)
            return "success";
        return "fail";
    }

    //删除分类
    @Action(value = "deleteCategory",
            results = {
                    @Result(name = "success",location = "/success.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String deleteCategory(){

        if(categoryService.deleteCategory(bookCategory) == 1)
            return "success";
        return "fail";
    }




    //用户所有分类
    @Action(value = "findAllCategory",
            results = {
                    @Result(name = "success",location = "/jsp/user/main.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String findAllCategory(){
        if((categoryService.findAllCategory())!=null){
            list = (ArrayList<BookCategory>) categoryService.findAllCategory();
            ActionContext.getContext().put("category_list", list);
        }

        else
            list = null;
        if(list == null)
            return "fail";
        return "success";
    }



    //管理员所有分类
    @Action(value = "admin_findAllCategory",
            results = {
                    @Result(name = "success",location = "/jsp/adminitor/show/category.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String admin_findAllCategory(){
        if((categoryService.findAllCategory())!=null){
            list = (ArrayList<BookCategory>) categoryService.findAllCategory();
            ActionContext.getContext().put("category_list", list);
        }

        else
            list = null;
        if(list == null)
            return "fail";
        return "success";
    }
}
