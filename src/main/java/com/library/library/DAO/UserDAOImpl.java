package com.library.library.DAO;

import java.util.List;

import com.library.library.bean.UserBean;

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
      String sql = "select * from user where account=?";
      UserBean user = jdbcTemplate.queryForObject(sql, UserBean.class, account);
      return user;
    } catch (Exception e) {
      return null;
    }
  }

}
