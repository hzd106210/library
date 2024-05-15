package com.library.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.DAO.ReaderDAOImpl;
import com.library.library.controller.ReaderVO.AddReader;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class ReaderController {
  @Autowired
  private ReaderDAOImpl readerDAOImpl;

  @PostMapping(value = "/addReader")
  public ResponseResult<Boolean> addReader(@Valid @RequestBody AddReader params) {
    boolean success = readerDAOImpl.addReader(params);
    if (success) {
      return ResponseResult.success(true, "添加成功");
    }
    return ResponseResult.fail("添加失败");
  }
}
