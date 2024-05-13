package com.library.library.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class BookBean {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  /* 封面 */
  private String cover;

  /* 书名 */
  private String name;

  /* 简介 */
  private String desc;

  /* 作者 */
  private String auth;

  /* 出版社 */
  @Column(name = "publishing_house")
  private String publishingHouse;

  /* 类型 */
  private String type;

  @Column(name = "book_type_id", nullable = true)
  private Long bookTypeId;

  @Column(name = "book_type_name", nullable = true)
  private String bookTypeName;

  /* 库存 */
  private int stock;

  /* 借阅数量 */
  @Column(name = "borrow_num")
  private int borrowNum;

  /* 创建时间 */
  @Column(name = "create_time")
  private Date createTime;

  /* 状态 1:上架 0:下架 */
  private int status;
}
