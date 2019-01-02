package com.three.utils;

import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public interface IBaseDao<T> {
    public int insert(T o);
    public int insert(List<T> list);

    public int delete(T o);
    public int delete(Class c, int id);
    public int deleteList(Class c, int... ids);            //删除多个id记录

    public T findById(Class c, int id);
    public T findOne(String hql, String[] param);
    public List<T> find(String hql, String[] param);
    public List<T> findPage(String hql, String param[], int page, int size);

    public int update(T o);

    public int getCount(String hql, String[] param);
    public List<T> findByFields(String hql, String[] fields, String []condition);

}
