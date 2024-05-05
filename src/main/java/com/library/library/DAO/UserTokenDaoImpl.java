package com.library.library.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("UserTokenDaoImpl")
public class UserTokenDaoImpl implements UserTokenDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public String findByUserId(String userId) {
    String sql = "select token from user_token where user_id=?";
    return jdbcTemplate.queryForObject(sql, String.class);
  }

  @Override
  public boolean insertToken(String userId, String token) {
    String sql = "select 1 from user_token where user_id=? limit 1";
    Number exist = jdbcTemplate.queryForObject(sql, Number.class);
    if (exist != null) {
      String sql2 = "insert into user_token (user_id, token) values (?, ?)";
      int update = jdbcTemplate.update(sql2, userId, token);
      return update > 0;
    } else {
      String sql2 = "update user_token set token=? where user_id=?";
      int update = jdbcTemplate.update(sql2, token, userId);
      return update > 0;
    }
  }

}
