package com.library.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.DAO.ReaderDAOImpl;
import com.library.library.bean.ListBean;
import com.library.library.bean.ReaderBean;
import com.library.library.controller.ReaderVO.AddReader;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reader")
public class ReaderController {
  @Autowired
  private ReaderDAOImpl readerDAOImpl;

  @PostMapping(value = "/getReaderList")
  public ResponseResult<ListBean<ReaderBean>> getReaderList(@Valid @RequestBody ReaderVO.GetReaderList params) {
    ListBean<ReaderBean> res = readerDAOImpl.getReaderList(params);
    return ResponseResult.success(res, "查询成功");
  }

  @PostMapping(value = "/addReader")
  public ResponseResult<Boolean> addReader(@Valid @RequestBody AddReader params) {
    boolean success = readerDAOImpl.addReader(params);
    if (success) {
      return ResponseResult.success(true, "添加成功");
    }
    return ResponseResult.fail("添加失败");
  }

  @PostMapping(value = "/updateStaus")
  public ResponseResult<Boolean> updateStaus(@Valid @RequestBody ReaderVO.UpdateStatus params) {
    int status = params.getStatus();
    boolean success = readerDAOImpl.updateStaus(params.getId(), status);
    if (success) {
      return ResponseResult.success(true, status == 1 ? "启用成功" : "停用成功");
    }
    return ResponseResult.fail(false, status == 1 ? "启用失败" : "停用失败");
  }
}
