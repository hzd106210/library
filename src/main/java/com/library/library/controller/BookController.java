package com.library.library.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.DAO.BookDAOImpl;
import com.library.library.bean.BookBean;
import com.library.library.bean.ListBean;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {
  @Autowired
  private BookDAOImpl bookDAOImpl;

  public ResponseResult<ListBean<BookBean>> queryAll(@Valid @RequestBody BookVO.QueryAll params) {
    ListBean<BookBean> list = bookDAOImpl.findAll(params);
    return ResponseResult.success(list, "查询成功");
  }

  @PostMapping(value = "/addBook")
  public ResponseResult<Boolean> addBook(@Valid @RequestBody(required = false) BookVO.AddBook params) {
    if (params == null) {
      return ResponseResult.fail(ResultCode.CLIENT_ERROR, "参数不能为空");
    }
    boolean hasBook = bookDAOImpl.hasBook(params.getName(), params.getAuth(), params.getPublishingHouse());
    if (hasBook) {
      return ResponseResult.fail("该书籍已存在");
    }
    BookBean bookBean = new BookBean();
    String cover = params.getCover();
    String type = params.getType();
    int stock = params.getStock();
    int status = params.getStatus();
    bookBean.setCover(cover == null ? "" : cover);
    bookBean.setName(params.getName());
    bookBean.setAuth(params.getAuth());
    bookBean.setDesc(params.getDesc());
    bookBean.setPublishingHouse(params.getPublishingHouse());
    bookBean.setType(type == null ? "" : type);
    // 默认库存为0
    bookBean.setStock(String.valueOf(stock).equals("") ? 0 : stock);
    bookBean.setBorrowNum(0);
    bookBean.setCreateTime(new Date());
    // 默认状态为下架
    bookBean.setStatus(String.valueOf(status).equals("") ? 0 : status);
    boolean insertSuccess = bookDAOImpl.addBook(bookBean);
    if (insertSuccess) {
      return ResponseResult.success(true, "添加成功");
    }
    return ResponseResult.fail("添加失败");
  }

  @PostMapping(value = "/updateBook")
  public ResponseResult<Boolean> updateBook(@Valid @RequestBody BookVO.UpdateBook params) {
    if (params == null) {
      return ResponseResult.fail(ResultCode.CLIENT_ERROR, "参数不能为空");
    }
    BookBean bookBean = new BookBean();
    String cover = params.getCover();
    String type = params.getType();
    int stock = params.getStock();
    int status = params.getStatus();
    bookBean.setId(params.getId());
    bookBean.setCover(cover == null ? "" : cover);
    bookBean.setName(params.getName());
    bookBean.setAuth(params.getAuth());
    bookBean.setDesc(params.getDesc());
    bookBean.setPublishingHouse(params.getPublishingHouse());
    bookBean.setAuth(type == null ? "" : type);
    bookBean.setStock(String.valueOf(stock).equals("") ? 0 : stock);
    bookBean.setBorrowNum(0);
    bookBean.setCreateTime(new Date());
    bookBean.setStatus(String.valueOf(status).equals("") ? 0 : status);
    boolean updateSuccess = bookDAOImpl.update(bookBean);
    if (updateSuccess) {
      return ResponseResult.success(true, "修改成功");
    }
    return ResponseResult.fail("修改失败");
  }
}
