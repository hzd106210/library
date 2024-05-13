package com.library.library.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.library.library.bean.BookBean;
import com.library.library.bean.ListBean;
import com.library.library.controller.BookVO;

@Service("BookDAOImpl")
public class BookDAOImpl implements BookDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public ListBean<BookBean> findAll(BookVO.QueryAll params) {
    int pageNo = params.getPageNo();
    Integer pageSize = params.getPageSize();
    if (pageSize == null) {
      pageSize = 10;
    }
    int startRow = (pageNo - 1) * pageSize;
    String name = params.getName();
    String publishingHouse = params.getPublishingHouse();
    Integer status = params.getStatus();
    Integer stock = params.getStock();
    Integer borrowNum = params.getBorrowNum();
    String sql = "select b.id,b.cover,b.name,b.auth,b.desc,b.publishing_house,b.stock,b.borrow_num,b.create_time,b.status,bt.id as book_type_id,bt.name as book_type_name from book b left join book_type bt on b.type=bt.id";
    String sql2 = "select count(b.id) from book b";
    List<Object> queryList = new ArrayList<>();
    List<Object> countList = new ArrayList<>();
    if (pageNo != 1) {
      sql += " where b.create_time < (select create_time from book order by create_time desc limit ?,1)";
      queryList.add(startRow == 0 ? 0 : startRow - 1);
    } else {
      sql += " where b.create_time > 0";
    }
    sql2 += " where b.id > 0";

    if (name != null) {
      String _sql = " and (b.auth like '%?' or b.name like '%?')";
      sql += _sql;
      sql2 += _sql;
      queryList.add(name);
      countList.add(name);
    }
    if (publishingHouse != null) {
      String _sql = " and b.publishing_house like '%" + publishingHouse + "%'";
      sql += _sql;
      sql2 += _sql;
    }
    if (status != null) {
      String _sql = " and b.status=?";
      sql += _sql;
      sql2 += _sql;
      queryList.add(status);
      countList.add(status);
    }
    if (stock != null) {
      String _sql = " and b.stock>=?";
      sql += _sql;
      sql2 += _sql;
      queryList.add(stock);
      countList.add(stock);
    }
    if (borrowNum != null) {
      String _sql = " and b.borrow_num>=?";
      sql += _sql;
      sql2 += _sql + ";";
      queryList.add(borrowNum);
      countList.add(borrowNum);
    }
    Integer count = jdbcTemplate.queryForObject(sql2, Integer.class, countList.toArray());
    sql += " order by b.create_time desc limit ?";
    queryList.add(pageSize);
    List<BookBean> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<BookBean>(BookBean.class),
        queryList.toArray());
    ListBean<BookBean> res = new ListBean<BookBean>(list, count == null ? 0 : count.intValue());
    return res;
  }

  @Override
  public BookBean findOne(int id) {
    String sql = "select * from book where id=?";
    BookBean book = jdbcTemplate.queryForObject(sql, BookBean.class, id);
    return book;
  }

  @Override
  public boolean updateStatus(int id, int status) {
    String sql = "update book where id=? set status=?";
    int update = jdbcTemplate.update(sql, id, status);
    return update > 0;
  }

  @Override
  public boolean update(BookBean book) {
    String sql = "update book set `cover`=?,`name`=?,`desc`=?,`auth`=?,`publishing_house`=?,`type`=?,`stock`=?,`status`=? where `id`=?";
    int update = jdbcTemplate.update(sql, book.getCover(), book.getName(), book.getDesc(), book.getAuth(),
        book.getPublishingHouse(),
        book.getType(), book.getStock(), book.getStatus(), book.getId());
    return update > 0;
  }

  @Override
  public boolean addBook(BookBean book) {
    String sql = "insert into book (`cover`,`name`,`desc`,`auth`,`publishing_house`,`type`,`stock`,`borrow_num`,`create_time`,`status`) values (?,?,?,?,?,?,?,?,?,?);";
    int update = jdbcTemplate.update(sql, book.getCover(), book.getName(), book.getDesc(), book.getAuth(),
        book.getPublishingHouse(),
        book.getType(), book.getStock(), 0, new Date(), 0);
    return update > 0;
  }

  @Override
  public int count() {
    String sql = "select count(id) from book";
    Integer num = jdbcTemplate.queryForObject(sql, Integer.class);
    return num == null ? 0 : num;
  }

  @Override
  public boolean hasBook(String name, String auth, String publishingHouse) {
    try {
      String sql = "select id from book where name=? and auth=? and publishing_house=?;";
      Integer exist = jdbcTemplate.queryForObject(sql, Integer.class, name, auth, publishingHouse);
      return exist != null;
    } catch (DataAccessException e) {
      if (e.getMessage().indexOf("expected 1") > -1) {
        return false;
      } else {
        throw e;
      }
    }
  }

  @Override
  public boolean deleteBook(long id) {
    String sql = "delete from book where `id`=?";
    int update = jdbcTemplate.update(sql, id);
    return update > 0;
  }

  @Override
  public boolean isSameBook(Map<String, Object> params) {
    try {
      String sql = "select 1 from book where `name`=? and `auth`=? and `publishing_house`=? and `id`!=?";
      Integer exist = jdbcTemplate.queryForObject(sql, Integer.class, params.get("name"), params.get("auth"),
          params.get("publishingHouse"), params.get("id"));
      return exist == null;
    } catch (DataAccessException e) {
      if (e.getMessage().indexOf("expected 1") > -1) {
        return true;
      } else {
        throw e;
      }
    }
  }

  @Override
  public boolean updateBookStatus(Map<String, Object> params) {
    long id = Long.parseLong(params.get("id").toString());
    int status = Integer.parseInt(params.get("status").toString());
    String sql = "update book set `status`=? where `id`=?";
    int update = jdbcTemplate.update(sql, status, id);
    return update > 0;
  }

}
