package com.library.library.DAO;

import java.util.List;

import com.library.library.bean.BookTypeBean;
import com.library.library.bean.ListBean;
import com.library.library.bean.PageBean;

public interface BookTypeDAO {
  ListBean<BookTypeBean> findAll(PageBean params);

  List<BookTypeBean> findAll();

  boolean addType(String name);

  boolean deleteTypeById(long id);

  boolean hasType(String name);

  BookTypeBean findById(long id);
}
