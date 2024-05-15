package com.library.library.DAO;

import java.util.List;

import com.library.library.bean.ListBean;
import com.library.library.bean.UserBean;
import com.library.library.bean.UserResultBean;
import com.library.library.controller.UserVO;

public interface UserDAO {
  List<UserBean> findAll();

  /**
   * 通过账号和密码查找
   * 
   * @param account  账号
   * @param password 密码
   * @return
   */
  boolean findByAccountAndPassword(String account, String password);

  /**
   * 添加用户
   * 
   * @param user 用户信息
   * @return
   */
  boolean addUser(UserBean user);

  /**
   * 修改密码
   * 
   * @param id
   * @param password
   * @return
   */
  boolean updatePassword(long id, String password);

  /**
   * 通过账号查找
   * 
   * @param account
   * @return
   */
  UserBean findByAccount(String account);

  /**
   * 通过账号查找该账号是否存在
   * 
   * @param account
   * @return
   */
  boolean findByAccont(String account);

  ListBean<UserResultBean> findAllUser(UserVO.GetAllUser params);

  boolean updateStatus(long id, int status);
}
