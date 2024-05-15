package com.library.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.library.library.bean.LibraryBean;
import com.library.library.bean.ListBean;
import com.library.library.controller.LIbraryVO;
import com.library.library.controller.LIbraryVO.FindAllLibrary;
import com.library.library.utils.SQL;

@Service("LIbraryDAOImpl")
public class LIbraryDAOImpl implements LIbraryDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public ListBean<LibraryBean> findAllLibrary(FindAllLibrary params) {
    int pageNo = params.getPageNo();
    Integer pageSize = params.getPageSize();
    if (pageSize == null) {
      pageSize = 10;
    }
    int startRow = (pageNo - 1) * pageSize;
    String name = params.getName();

    String sql = "select * from library";
    String sql2 = "select count(id) from library";
    List<Object> queryList = new ArrayList<>();
    List<Object> countList = new ArrayList<>();

    if (pageNo != 1) {
      sql += " where id < (select id from library order by id desc limit ?,1)";
      queryList.add(startRow == 0 ? 0 : startRow - 1);
    }

    if (name != null && !name.equals("")) {
      String _sql = " name like '%" + name + "%'";
      sql = SQL.insertSqlWhereAnd(sql, _sql);
      sql2 = SQL.insertSqlWhereAnd(sql2, _sql);
    }

    sql += " order by id desc limit ?";
    queryList.add(pageSize.intValue());

    List<LibraryBean> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<LibraryBean>(LibraryBean.class),
        queryList.toArray());
    Integer count = jdbcTemplate.queryForObject(sql2, Integer.class, countList.toArray());
    ListBean<LibraryBean> res = new ListBean<LibraryBean>(list, count);
    return res;
  }

  @Override
  public boolean addLibrary(LIbraryVO.AddLibrary data) {
    String sql = "insert into library (name,address,contact,contact_person) values (?,?,?,?)";
    ArrayList<Object> params = new ArrayList<>();
    params.add(data.getName());
    params.add(data.getAddress());
    params.add(data.getContact());
    params.add(data.getContactPerson());
    int update = jdbcTemplate.update(sql, params.toArray());
    return update > 0;
  }

  @Override
  public boolean updateLibrary(LibraryBean data) {
    String sql = "update library set name=?,address=?,contact=?,contact_person=? where id=?";
    ArrayList<Object> params = new ArrayList<>();
    params.add(data.getName());
    params.add(data.getAddress());
    params.add(data.getContact());
    params.add(data.getContactPerson());
    params.add(data.getId());
    int update = jdbcTemplate.update(sql, params.toArray());
    return update > 0;
  }

  @Override
  public boolean deleteLibrary(long id) {
    String sql = "delete from library where id=?";
    int update = jdbcTemplate.update(sql, id);
    return update > 0;
  }

}
