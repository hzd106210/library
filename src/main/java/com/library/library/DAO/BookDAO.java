package com.library.library.DAO;

import java.util.Map;

import com.library.library.bean.BookBean;
import com.library.library.bean.ListBean;
import com.library.library.controller.BookVO;

public interface BookDAO {
  ListBean<BookBean> findAll(BookVO.QueryAll params);

  BookBean findOne(int id);

  boolean updateStatus(int id, int status);

  boolean update(BookBean book);

  boolean addBook(BookBean book);

  int count();

  boolean hasBook(String name, String auth, String publishingHouse);

  boolean isSameBook(Map<String, Object> params);

  boolean deleteBook(long id);

  boolean updateBookStatus(Map<String, Object> params);
}
