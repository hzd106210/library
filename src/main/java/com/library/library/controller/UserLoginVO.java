package com.library.library.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginVO {
  @NotEmpty(message = "账号不能为空")
  private String account;

  @NotEmpty(message = "密码不能为空")
  private String password;
}
