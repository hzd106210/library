package com.library.library.DAO;

public interface UserTokenDAO {
  /*
   * 通过userid获取token
   */
  String findByUserId(String userId);

  /*
   * 插入token
   */
  boolean insertToken(String userId, String token);
}
