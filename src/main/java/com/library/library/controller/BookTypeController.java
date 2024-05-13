package com.library.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.DAO.BookTypeDAOImpl;
import com.library.library.bean.BookTypeBean;
import com.library.library.bean.ListBean;
import com.library.library.bean.PageBean;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookType")
public class BookTypeController {
  @Autowired
  private BookTypeDAOImpl bookTypeDAOImpl;

  @PostMapping(value = "/getAll")
  public ResponseResult<List<BookTypeBean>> getAll() {
    List<BookTypeBean> list = bookTypeDAOImpl.findAll();
    return ResponseResult.success(list, "查询成功");
  }

  @PostMapping(value = "/findAll")
  public ResponseResult<ListBean<BookTypeBean>> findAll(@Valid @RequestBody PageBean params) {
    ListBean<BookTypeBean> all = bookTypeDAOImpl.findAll(params);
    return ResponseResult.success(all, "查询成功");
  }

  @PostMapping(value = "/addType")
  public ResponseResult<Boolean> addType(@Valid @RequestBody BookTypeVO.AddType params) {
    String name = params.getName();
    boolean hasType = bookTypeDAOImpl.hasType(name);
    if (hasType) {
      return ResponseResult.fail("该类别已存在");
    }
    boolean addSuccess = bookTypeDAOImpl.addType(name);
    return ResponseResult.success(addSuccess, addSuccess ? "添加成功" : "添加失败");
  }

  @PostMapping(value = "/delete")
  public ResponseResult<Boolean> delete(@Valid @RequestBody BookTypeVO.DeleteType params) {
    boolean deleteTypeById = bookTypeDAOImpl.deleteTypeById(params.getId());
    if (!deleteTypeById) {
      return ResponseResult.fail("删除失败");
    }
    return ResponseResult.success(true, "删除成功");
  }
}
