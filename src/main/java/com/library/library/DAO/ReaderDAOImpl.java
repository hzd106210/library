package com.library.library.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;

import com.library.library.bean.ListBean;
import com.library.library.bean.ReaderBean;
import com.library.library.controller.ReaderVO;
import com.library.library.controller.ReaderVO.AddReader;
import com.library.library.utils.SQL;

@Service("ReaderDAOImpl")
public class ReaderDAOImpl implements ReaderDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @PostMapping(value = "/getReaderList")
  public ListBean<ReaderBean> getReaderList(ReaderVO.GetReaderList params) {
    int pageNo = params.getPageNo();
    Integer pageSize = params.getPageSize();
    if (pageSize == null) {
      pageSize = 10;
    }
    int startRow = (pageNo - 1) * pageSize;
    String name = params.getName();

    String sql = "select r.*,l.name as library_name from reader r left join library l on r.library_id=l.id";
    String sql2 = "select count(r.id) from reader r";
    List<Object> queryList = new ArrayList<>();

    if (pageNo != 1 && pageNo != -1) {
      sql += " where r.id < (select id from reader order by id desc limit ?,1)";
      queryList.add(startRow == 0 ? 0 : startRow - 1);
    }

    if (name != null && !name.equals("")) {
      String _sql = " r.name like '%" + name + "%'";
      sql = SQL.insertSqlWhereAnd(sql, _sql);
      sql2 = SQL.insertSqlWhereAnd(sql2, _sql);
    }

    if (pageNo != -1) {
      sql += " order by r.id desc limit ?";
      queryList.add(pageSize.intValue());
    }

    List<ReaderBean> list = jdbcTemplate.query(sql,
        new BeanPropertyRowMapper<ReaderBean>(ReaderBean.class), queryList.toArray());
    int count;

    if (pageNo == -1) {
      count = list.size();
    } else {
      count = jdbcTemplate.queryForObject(sql2, Integer.class);
    }
    ListBean<ReaderBean> res = new ListBean<ReaderBean>(list, count);
    return res;
  }

  @Override
  public boolean addReader(AddReader params) {
    String sql = "insert into reader (name,type,contact,borrow_card_number,library_id,user_id,status) values (?,?,?,?,?,?,?)";
    ArrayList<Object> arrayList = new ArrayList<>();
    arrayList.add(params.getName());
    arrayList.add(params.getType());
    arrayList.add(params.getContact());
    long time = new Date().getTime();
    String borrowCardNumber = DigestUtils.md5DigestAsHex(String.valueOf(time).getBytes());
    arrayList.add(borrowCardNumber);
    arrayList.add(params.getLibraryId());
    arrayList.add(params.getUserId());
    arrayList.add(1);
    int update = jdbcTemplate.update(sql, arrayList.toArray());
    return update > 0;
  }

  @Override
  public boolean updateStaus(long id, int status) {
    String sql = "update reader set status=? where id=?";
    int update = jdbcTemplate.update(sql, status, id);
    return update > 0;
  }

}
