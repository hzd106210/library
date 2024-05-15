package com.library.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.DAO.LIbraryDAOImpl;
import com.library.library.bean.LibraryBean;
import com.library.library.bean.ListBean;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/library")
public class LibraryController {
  @Autowired
  private LIbraryDAOImpl lIbraryDAOImpl;

  @PostMapping(value = "/getAllLibrary")
  public ResponseResult<ListBean<LibraryBean>> getAllLibrary(@Valid @RequestBody LIbraryVO.FindAllLibrary params) {
    ListBean<LibraryBean> res = lIbraryDAOImpl.findAllLibrary(params);
    return ResponseResult.success(res, "查询成功");
  }
}
