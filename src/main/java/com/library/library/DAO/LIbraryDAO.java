package com.library.library.DAO;

import com.library.library.bean.LibraryBean;
import com.library.library.bean.ListBean;
import com.library.library.controller.LIbraryVO;

public interface LIbraryDAO {
  ListBean<LibraryBean> findAllLibrary(LIbraryVO.FindAllLibrary params);

  boolean addLibrary(LIbraryVO.AddLibrary data);

  boolean updateLibrary(LibraryBean data);

  boolean deleteLibrary(long id);
}
