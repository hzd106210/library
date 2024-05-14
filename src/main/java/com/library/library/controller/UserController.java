package com.library.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.DAO.UserDAOImpl;
import com.library.library.DAO.UserTokenDaoImpl;
import com.library.library.DAO.UserTypeDAOImpl;
import com.library.library.bean.UserBean;
import com.library.library.utils.UserToken;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserDAOImpl userDAOImpl;

  @Autowired
  private UserTokenDaoImpl userTokenDaoImpl;

  @Autowired
  private UserTypeDAOImpl userTypeDAOImpl;

  private static final String PASSWORD_STR = "__library__";

  /*
   * 登录
   */
  @RequestMapping(method = RequestMethod.POST, value = "/login")
  public ResponseResult<UserVO.LoginResult> login(@Valid @RequestBody(required = false) UserVO.Login params) {
    if (params == null) {
      return ResponseResult.fail(ResultCode.CLIENT_ERROR, "参数不能为空");
    }
    String msg = "";
    UserBean user = userDAOImpl.findByAccount(params.getAccount());
    if (user == null) {
      msg = "账号不存在";
    } else {
      String password = params.getPassword();
      String newPassword = PASSWORD_STR + password;
      String md5Password = DigestUtils.md5DigestAsHex(newPassword.getBytes());
      if (!user.getPassword().equals(md5Password)) {
        msg = "密码错误";
      } else {
        String userId = user.getUserId();
        String token = UserToken.getToken(userId);

        // 保存token
        userTokenDaoImpl.insertToken(userId, token);

        int type = userTypeDAOImpl.findType(user.getTypeId());
        return ResponseResult.success(new UserVO.LoginResult(token, type), "登录成功");
      }
    }
    return ResponseResult.fail(null, msg);
  }

  /*
   * 注册
   */
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ResponseResult<String> register(@Valid @RequestBody(required = false) UserVO.Register params) {
    if (params == null) {
      return ResponseResult.fail(ResultCode.CLIENT_ERROR, "参数不能为空");
    }
    String account = params.getAccount();
    boolean isHasAccount = userDAOImpl.findByAccont(account);
    if (isHasAccount) {
      // 账号已存在
      return ResponseResult.fail(ResultCode.CLIENT_ERROR, "该账号已存在");
    }
    String password = params.getPassword();
    String newPassword = PASSWORD_STR + password;
    String md5Password = DigestUtils.md5DigestAsHex(newPassword.getBytes());
    String userId = String.valueOf(System.currentTimeMillis());

    UserBean userBean = new UserBean(0,
        account,
        params.getUsername(), md5Password, "", 2, 1, userId);
    boolean isInsertSuccess = userDAOImpl.addUser(userBean);
    if (isInsertSuccess) {
      String token = UserToken.getToken(userId);
      // 保存token
      userTokenDaoImpl.insertToken(userId, token);
      return ResponseResult.success(token, "注册成功");
    }
    return ResponseResult.fail("注册失败");
  }

  /**
   * 修改密码
   * 
   * @param params
   * @return
   */
  @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
  public ResponseResult<Boolean> updatePassword(@Valid @RequestBody(required = false) UserVO.UpdatePassword params) {
    if (params == null) {
      return ResponseResult.fail(ResultCode.CLIENT_ERROR, "参数不能为空");
    }

    return ResponseResult.fail("修改失败");
  }
}
