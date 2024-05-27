package com.library.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.DAO.LoanDAOImpl;
import com.library.library.bean.ListBean;
import com.library.library.bean.LoanBean;
import com.library.library.bean.LoanBean.GetListParams;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loan")
public class LoanController {
  @Autowired
  private LoanDAOImpl loanDAOImpl;

  @PostMapping(value = "/getLoanList")
  public ResponseResult<ListBean<LoanBean>> getLoanList(@Valid @RequestBody GetListParams params) {
    ListBean<LoanBean> res = loanDAOImpl.getLoanList(params);
    return ResponseResult.success(res, "查询成功");
  }
}
