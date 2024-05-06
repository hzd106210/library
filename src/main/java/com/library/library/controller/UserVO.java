package com.library.library.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class UserVO {
  /*
   * 修改密码参数
   */
  @Data
  static class UpdatePassword {
    @NotEmpty(message = "账号不能为空")
    private String account;

    @NotEmpty(message = "密码不能为空")
    private String password;
  }

  /*
   * 登录参数
   */
  @Data
  static class Login {
    @NotEmpty(message = "账号不能为空")
    private String account;

    @NotEmpty(message = "密码不能为空")
    private String password;
  }

  /*
   * 注册参数
   */
  @Data
  static class Register {
    @NotNull(message = "账号不能为空")
    private String account;
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
  }

}
