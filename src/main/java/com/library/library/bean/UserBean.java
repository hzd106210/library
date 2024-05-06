package com.library.library.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBean {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String account;
  private String username;
  private String password;
  private String avatar;
  @Column(name = "type_id")
  private int typeId;
  private int status;
  @Column(name = "user_id")
  private String userId;
  // TODO 缺少注册时间 登录时间需要？？
}
