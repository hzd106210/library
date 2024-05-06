package com.library.library.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterVO {
  @NotNull(message = "账号不能为空")
  private String account;
  @NotEmpty(message = "用户名不能为空")
  private String username;
  @NotEmpty(message = "密码不能为空")
  private String password;
}
