package com.library.library.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    int pageSize = params.getPageSize();
    int startRow = (pageNo - 1) * pageSize;
    String name = params.getName();
    String publishingHouse = params.getPublishingHouse();
    int status = params.getStatus();
    int stock = params.getStock();
    int borrowNum = params.getBorrowNum();
    String sql = "select * from book where create_time > (select create_time from book order by create_time desc limit ?,1) ";
    List<Object> queryList = new ArrayList<>();
    queryList.add(startRow - 1);
    if (!name.equals("")) {
      sql += "and (auth like '%?' or name like '%?')";
      queryList.add(name);
    }
    if (!publishingHouse.equals("")) {
      sql += "and publishing_house like '%?'";
      queryList.add(publishingHouse);
    }
    if (!String.valueOf(status).equals("")) {
      sql += "and status=?";
      queryList.add(status);
    }
    if (!String.valueOf(stock).equals("")) {
      sql += "and stock>=?";
      queryList.add(stock);
    }
    if (!String.valueOf(borrowNum).equals("")) {
      sql += "and borrow_num>=?";
      queryList.add(borrowNum);
    }
    sql += "limit ?;";
    queryList.add(pageSize);
    List<BookBean> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<BookBean>(BookBean.class),
        queryList.toArray());
    int count = this.count();
    ListBean<BookBean> res = new ListBean<BookBean>(list, count);
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
    String sql = "update book set cover=?,name=?,desc=?,auth=?,publishing_house=?,type=?,stock=?,status=? where id=?";
    int update = jdbcTemplate.update(sql, book.getCover(), book.getName(), book.getDesc(), book.getAuth(),
        book.getPublishingHouse(),
        book.getType(), book.getStock(), book.getStatus(), book.getId());
    return update > 0;
  }

  @Override
  public boolean addBook(BookBean book) {
    String sql = "insert into book (cover,name,desc,auth,publishing_house,type,stock,borrow_num,create_time,status) values (?,?,?,?,?,?,?,?,?,?)";
    int update = jdbcTemplate.update(sql, book.getCover(), book.getName(), book.getDesc(), book.getPublishingHouse(),
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
    String sql = "select 1 from book where name=? and auth=? and publishing_house=? limit 1";
    Integer exist = jdbcTemplate.queryForObject(sql, Integer.class, name, auth, publishingHouse);
    return exist != null;
  }

}
