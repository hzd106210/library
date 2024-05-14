package com.library.library.DAO;

import java.util.ArrayList;
import java.util.List;

import com.library.library.bean.ListBean;
import com.library.library.bean.UserBean;
import com.library.library.bean.UserResultBean;
import com.library.library.controller.UserVO.GetAllUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("UserDAOImpl")
public class UserDAOImpl implements UserDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<UserBean> findAll() {
    String sql = "select * from user";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class));
  }

  @Override
  public boolean findByAccountAndPassword(String account, String password) {
    UserBean byAccount = findByAccount(account);
    if (byAccount == null) {
      return false;
    } else {
      try {
        String sql = "select * from user where account=? and password=?";
        UserBean user = jdbcTemplate.queryForObject(sql, UserBean.class);
        return user != null;
      } catch (Exception e) {
        return false;
      }
    }
  }

  @Override
  public boolean addUser(UserBean user) {
    try {
      String sql = "insert into user (account,username,password,avatar,type_id,status,user_id) values (?,?,?,?,?,?,?)";
      int update = jdbcTemplate.update(sql, user.getAccount(), user.getUsername(), user.getPassword(), user.getAvatar(),
          user.getTypeId(), user.getStatus(), user.getUserId());
      return update > 0;
    } catch (DataAccessException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean updatePassword(long id, String password) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
  }

  @Override
  public UserBean findByAccount(String account) {
    try {
      String sql = "select * from user where `account`=?";
      List<UserBean> user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class), account);
      return user.size() > 0 ? user.get(0) : null;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public boolean findByAccont(String account) {
    try {
      String sql = "select 1 from user where account=? limit 1";
      Number exist = jdbcTemplate.queryForObject(sql, Number.class, account);
      return exist != null;
    } catch (DataAccessException e) {
      return false;
    }
  }

  @Override
  public ListBean<UserResultBean> findAllUser(GetAllUser params) {
    int pageNo = params.getPageNo();
    Integer pageSize = params.getPageSize();
    if (pageSize == null) {
      pageSize = 10;
    }
    String sql = "select u.id,u.username,u.avatar,u.status,u.user_id,ut.type from user u left join user_type ut on u.type_id=ut.id";
    String sql2 = "select count(u.id) from user u";

    int startRow = (pageNo - 1) * pageSize;
    String userId = params.getUserId();
    Object username = params.getUsername();
    ArrayList<Object> queryList = new ArrayList<>();
    ArrayList<Object> countList = new ArrayList<>();
    if (pageNo != 1) {
      sql += " where u.id < (select id from user order by id desc limit ?,1)";
      queryList.add(startRow == 0 ? 0 : startRow - 1);
    }

    if (userId != null) {
      sql += " and b.user_id=?";
      sql2 += (countList.size() > 0 ? "" : " where") + " b.user_id=?";
      queryList.add(userId);
      countList.add(userId);
    }

    if (username != null) {
      sql += " and b.username=?";
      sql2 += (countList.size() > 0 ? "" : " where") + " b.user_id=?";
      queryList.add(username);
      countList.add(username);
    }

    sql += " order by u.id desc limit ?";
    queryList.add(pageSize);

    List<UserResultBean> userList = jdbcTemplate.query(sql,
        new BeanPropertyRowMapper<UserResultBean>(UserResultBean.class),
        queryList.toArray());
    Integer total = jdbcTemplate.queryForObject(sql2, Integer.class, countList.toArray());
    ListBean<UserResultBean> res = new ListBean<UserResultBean>(userList, total);
    return res;
  }

}
