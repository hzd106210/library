package com.library.library.controller;

import org.springframework.context.annotation.Primary;

import com.library.library.bean.PageBean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @NotNull(message = "性别不能为空")
    private int gender;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class LoginResult {
    private String token;
    private int type;

  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = false)
  public static class GetAllUser extends PageBean {
    private String username;
    private String userId;
  }

  @Data
  public static class UpdateStatus {
    @NotNull(message = "id不能为空")
    private long id;

    @NotNull(message = "status不能为空")
    private int status;
  }

}
