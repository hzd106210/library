package com.library.library.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeBean {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private int type;
  private String desc;
}
