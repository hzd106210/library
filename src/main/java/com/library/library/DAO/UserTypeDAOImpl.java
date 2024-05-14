package com.library.library.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("UserTypeDAOImpl")
public class UserTypeDAOImpl implements UserTypeDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int findType(long id) {
    String sql = "select type from user_type where id=?";
    Integer type = jdbcTemplate.queryForObject(sql, Integer.class, id);
    return type.intValue();
  }

}
