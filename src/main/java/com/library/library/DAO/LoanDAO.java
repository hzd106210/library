package com.library.library.DAO;

import com.library.library.bean.ListBean;
import com.library.library.bean.LoanBean;
import com.library.library.bean.LoanBean.AddLoanParams;

public interface LoanDAO {
  ListBean<LoanBean> getLoanList(LoanBean.GetListParams params);

  boolean addLoan(AddLoanParams params);
}
