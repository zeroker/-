package com.three.action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.three.beans.Book;
import com.three.beans.BookCategory;
import com.three.service.book.BookService;
import com.three.service.category.CategoryService;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@Namespace("/book")
@Scope(value = "prototype")
@ParentPackage(value = "struts-default")
public class BookAction extends ActionSupport {

    private Book book;
    private BookCategory category;

    private File file;                                    //
    private String fileFileName;
    private String fileContentType;
    private ArrayList<Book> list;

    private static Logger logger = LogManager.getLogger(BookAction.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;


    public ArrayList<Book> getList() {
        return list;
    }

    public void setList(ArrayList<Book> list) {
        this.list = list;
    }


    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    @Action(value = "addBook",
            results = {
                    @Result(name = "success",location = "/success.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String addBook  () throws IOException {

        logger.info(category.getCategory());

        String c_id = categoryService.findOne(category).getC_id();
        String realPath = ServletActionContext.getServletContext().getRealPath("/file");

        if(file != null){
            File saveFile = new File(new File(realPath),fileFileName);
            if(!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdir();
            }
            FileUtils.copyFile(file,saveFile);

        }
        book.setPic(fileFileName);
        book.setB_id(c_id);                                                //
        int n = bookService.addBook(book);
        if(n == 1)
            return SUCCESS;
        else
            return "fail";

    }


//ok但是没有删除 图片
    @Action(value = "deleteBook",
            results = {
                    @Result(name = "success",location = "/success.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String deletedBook(){
        int n = bookService.deleteBook(book);
        if(n == 1)
            return SUCCESS;
        else
            return "fail";

    }

//用户展示所有的书籍
    @Action(
            value = "findAllBooks",
            results = {
                    @Result(name = "success",type = "chain",
                            params = {"namespace","/category","actionName","findAllCategory"}),//吓一跳是找所有分类
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String showAllBooks(){

        List<Book> list = bookService.findAllBooks();
        if(list == null)
            return "fail";
        else
            ActionContext.getContext().put("list", list);
        return "success";

    }


    //管理员   展示所有的书籍
    @Action(
            value = "admin_findAllBooks",
            results = {
                    @Result(name = "success",type = "chain",
                            params = {"namespace","/category","actionName","admin_findAllCategory"}),//吓一跳是找所有分类
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String admin_showAllBooks(){

        List<Book> list = bookService.findAllBooks();
        if(list == null)
            return "fail";
        else
            ActionContext.getContext().put("list", list);
        return "success";

    }


    //用户分类查找
    @Action(
            value = "showBooksByCategory",
            results = {
                    @Result(name = "success",type = "chain",
                            params = {"namespace","/category","actionName","findAllCategory"}),//吓一跳是找所有分类
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String showBooksByCategory(){

        logger.info(category.getCategory());
        List<Book> listBook = bookService.findByCategory(category);

        if(listBook == null)
            return "fail";
        else
            ActionContext.getContext().put("list", listBook);
        return "success";
    }


//
//    //管理员分类查找
//    @Action(
//            value = "admin_showBooksByCategory",
//            results = {
//                    @Result(name = "success",type = "chain",
//                            params = {"namespace","/category","actionName","admin_findAllCategory"}),//吓一跳是找所有分类
//                    @Result(name = "fail",location = "/index.jsp")
//            }
//    )
//    public String admin_showBooksByCategory(){
//
//        logger.info(category.getCategory());
//        List<Book> listBook = bookService.findByCategory(category);
//
//        if(listBook == null)
//            return "fail";
//        else
//            ActionContext.getContext().put("list", listBook);
//        return "success";
//    }


    @Action(
            value = "showBooksByPro",
            results = {
                    @Result(name = "success",location = "/jsp/user/main.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String showBooksByPro(){

        logger.info("anthor?--->"+book.getAuthor());
        logger.info("name?--->"+book.getName());
        if(book.getName().equals(""))
            logger.info("name is jiakongge");
        if(book.getName().equals(""))
            logger.info("name is kongge");
        list = (ArrayList<Book>) bookService.findByPro(book);
        if(list == null)
            return "fail";
        else
            ActionContext.getContext().put("list", list);
        return "success";
    }





    //
    @Action(
            value = "showOneBookInfo",
            results = {
                    @Result(name = "success",location = "/success.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String showOneBookInfo(){
        String realPath = ServletActionContext.getServletContext().getRealPath("/file");
        book = bookService.findByBookName(book);
        fileFileName = book.getPic();
//        String url = realPath+"/"+fileFileName;
//        fileFileName = url;
        if(fileFileName == null)
            return "fail";
        else
            return  "success";
    }


    @Action(
            value = "showBooksByProName",
            results = {
                    @Result(name = "success",location = "/success.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String showBooksByProName(){

//        logger.info("anthor?--->"+book.getAuthor());
        list = (ArrayList<Book>) bookService.findByBookProName(book);
        if(list == null)
            return "fail";
        else
            ActionContext.getContext().put("list", list);
        return "success";
    }






}
