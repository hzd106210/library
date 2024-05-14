package com.library.library.bean;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResultBean {
  private long id;
  private String username;
  private String avatar;
  private int type;
  private int status;
  @Column(name = "user_id")
  private String userId;

}
