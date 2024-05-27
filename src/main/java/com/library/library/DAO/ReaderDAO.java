package com.library.library.DAO;

import com.library.library.bean.ListBean;
import com.library.library.bean.ReaderBean;
import com.library.library.controller.ReaderVO;

public interface ReaderDAO {
  ListBean<ReaderBean> getReaderList(ReaderVO.GetReaderList params);

  boolean addReader(ReaderVO.AddReader params);

  boolean updateStaus(long id, int status);
}
