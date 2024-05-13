package com.library.library.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.library.library.bean.BookTypeBean;
import com.library.library.bean.ListBean;
import com.library.library.bean.PageBean;

@Service("BookTypeDAOImpl")
public class BookTypeDAOImpl implements BookTypeDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public boolean addType(String name) {
    String sql = "insert into book_type (`name`) values (?)";
    int update = jdbcTemplate.update(sql, name);
    return update > 0;
  }

  @Override
  public ListBean<BookTypeBean> findAll(PageBean params) {
    int pageNo = params.getPageNo();
    Integer pageSize = params.getPageSize();
    if (pageSize == null) {
      pageSize = 10;
    }
    int startRow = (pageNo - 1) * pageSize;
    String sql = "select * from book_type order by id desc limit ?,?";
    List<BookTypeBean> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<BookTypeBean>(BookTypeBean.class),
        startRow == 0 ? 0 : startRow, pageSize);
    String sql2 = "select count(id) from book_type";
    Integer count = jdbcTemplate.queryForObject(sql2, Integer.class);
    ListBean<BookTypeBean> res = new ListBean<BookTypeBean>(list, count == null ? 0 : count.intValue());
    return res;
  }

  @Override
  public boolean deleteTypeById(long id) {
    String sql = "delete from book_type where id=?";
    int update = jdbcTemplate.update(sql, id);
    return update > 0;
  }

  @Override
  public boolean hasType(String name) {
    String sql = "select count(id) from book_type where name=?";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name);
    return count > 0;
  }

  @Override
  public List<BookTypeBean> findAll() {
    String sql = "select * from book_type order by id desc";
    List<BookTypeBean> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<BookTypeBean>(BookTypeBean.class));
    return list;
  }
}
