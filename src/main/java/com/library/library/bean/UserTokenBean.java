package com.library.library.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTokenBean {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String user_id;
  private String token;
}
