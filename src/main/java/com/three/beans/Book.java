package com.three.beans;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "t_book_info", schema = "ssh")
public class Book implements Serializable {
    private int id;
    private  String b_id;
    private String name;
    private String author;
    private Integer price;
    private String version;
    private String pic;
    private int num;
    private BookCategory bookCategory;

    private int status;
    public Book(){}
    public Book(String name,String author,int price,int num,String pic,String version){
        this.name = name;
        this.author = author;
        this.price = price;
        this.num = num;
        this.pic = pic;
        this.version = version;

    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_id",referencedColumnName="c_id",insertable=false,updatable=false)
    public BookCategory getBookCategory() {
        return  bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this. bookCategory = bookCategory;
    }



    @Column(name = "b_id")                                 //
    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "pic")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "author", nullable = true, length = 50)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "price", nullable = true)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "version", nullable = true, length = 50)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (version != null ? !version.equals(book.version) : book.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
