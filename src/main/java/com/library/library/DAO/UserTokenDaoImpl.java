package com.library.library.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("UserTokenDaoImpl")
public class UserTokenDaoImpl implements UserTokenDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public String findByUserId(String userId) {
    try {
      String sql = "select token from user_token where user_id=?";
      return jdbcTemplate.queryForObject(sql, String.class, userId);
    } catch (DataAccessException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean insertToken(String userId, String token) {
    String sql = "select count(id) from user_token where user_id=?";
    Integer exist = jdbcTemplate.queryForObject(sql, Integer.class, userId);
    if (exist.intValue() == 1) {
      String sql2 = "update user_token set token=? where user_id=?";
      int update = jdbcTemplate.update(sql2, token, userId);
      return update > 0;
    } else {
      String sql2 = "insert into user_token (user_id, token) values (?, ?)";
      int update = jdbcTemplate.update(sql2, userId, token);
      return update > 0;
    }
  }

}
