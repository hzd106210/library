package com.library.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.library.library.bean.ListBean;
import com.library.library.bean.LoanBean;
import com.library.library.bean.LoanBean.AddLoanParams;
import com.library.library.bean.LoanBean.GetListParams;
import com.library.library.utils.SQL;

@Service("LoanDAOImpl")
public class LoanDAOImpl implements LoanDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public ListBean<LoanBean> getLoanList(GetListParams params) {
    String sql = "select l.*,r.name as reader_name,b.name as book_name from loan l left join reader r on l.reader_id=r.id left join book b on l.book_id=b.id";
    String sql2 = "select count(reader_id) from loan l";
    ArrayList<Object> queryList = new ArrayList<>();

    int pageNo = params.getPageNo();
    Integer pageSize = params.getPageSize();
    if (pageSize == null) {
      pageSize = 10;
    }

    if (pageNo != 1) {
      sql += " where l.id<(select id from loan order by id desc limit ?,1)";
      int startRow = (pageNo - 1) * pageSize;
      startRow = startRow == 0 ? 0 : startRow - 1;
      queryList.add(startRow);
    }

    String readerName = params.getReaderName();
    if (readerName != null) {
      String _sql = " r.name like '%" + readerName + "%'";
      sql = SQL.insertSqlWhereAnd(sql, _sql);
      sql2 = SQL.insertSqlWhereAnd(sql2, _sql);
    }

    sql += " order by l.id desc limit ?";
    queryList.add(pageSize);

    List<LoanBean> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<LoanBean>(LoanBean.class),
        queryList.toArray());
    Integer count = jdbcTemplate.queryForObject(sql2, Integer.class);
    ListBean<LoanBean> res = new ListBean<LoanBean>(list, count == null ? 0 : count);
    return res;
  }

  @Override
  public boolean addLoan(AddLoanParams params) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addLoan'");
  }

}
