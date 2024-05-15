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

  @PostMapping(value = "/addLibrary")
  public ResponseResult<Boolean> addLibrary(@Valid @RequestBody LIbraryVO.AddLibrary params) {
    boolean success = lIbraryDAOImpl.addLibrary(params);
    if (success) {
      return ResponseResult.success(true, "添加成功");
    }
    return ResponseResult.fail("添加失败");
  }

  @PostMapping(value = "/updateLibrary")
  public ResponseResult<Boolean> updateLibrary(@Valid @RequestBody LibraryBean params) {
    boolean success = lIbraryDAOImpl.updateLibrary(params);
    if (success) {
      return ResponseResult.success(true, "修改成功");
    }
    return ResponseResult.fail("修改失败");
  }

  @PostMapping(value = "/deleteLibrary")
  public ResponseResult<Boolean> deleteLibrary(@Valid @RequestBody LIbraryVO.DeleteLibrary params) {
    boolean success = lIbraryDAOImpl.deleteLibrary(params.getId());
    if (success) {
      return ResponseResult.success(true, "删除成功");
    }
    return ResponseResult.fail("删除失败");
  }
}
