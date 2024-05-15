package com.library.library.DAO;

import com.library.library.controller.ReaderVO;

public interface ReaderDAO {
  boolean addReader(ReaderVO.AddReader params);
}
