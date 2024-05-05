package com.library.library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.DAO.UserDAOImpl;
import com.library.library.DAO.UserTokenDaoImpl;
import com.library.library.bean.UserBean;
import com.library.library.utils.UserToken;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserDAOImpl userDAOImpl;

  @Autowired
  private UserTokenDaoImpl userTokenDaoImpl;

  private static final String PASSWORD_STR = "__library__";

  @RequestMapping(method = RequestMethod.POST, value = "/login")
  public ResponseResult<String> login(@RequestBody Map<String, Object> params) {
    String msg = "";
    if (params.get("account") == null) {
      msg = "请输入账号";
    } else if (params.get("password") == null) {
      msg = "请输入密码";
    } else {
      UserBean user = userDAOImpl.findByAccount(params.get("account").toString());
      if (user == null) {
        msg = "账号不存在";
      } else {
        String password = params.get("password").toString();
        String newPassword = PASSWORD_STR + password;
        String md5Password = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        if (user.getPassword() != md5Password) {
          msg = "密码错误";
        } else {
          String token = UserToken.getToken(user.getUserId());
          return ResponseResult.success(token, "登录成功");
        }
      }
    }
    return ResponseResult.fail(null, msg);
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ResponseResult<String> register(@Valid @RequestBody(required = true) UserVO user) {
    String password = user.getPassword();
    String newPassword = PASSWORD_STR + password;
    String md5Password = DigestUtils.md5DigestAsHex(newPassword.getBytes());
    String userId = String.valueOf(System.currentTimeMillis());

    UserBean userBean = new UserBean(0, user.getAccount(),
        user.getUsername(), md5Password, "", 1, 1, userId);
    boolean isInsertSuccess = userDAOImpl.addUser(userBean);
    if (isInsertSuccess) {
      String token = UserToken.getToken(userId);
      return ResponseResult.success(token, "注册成功");
    }
    return ResponseResult.fail("注册失败");
  }
}
