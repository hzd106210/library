package com.library.library.controller;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

public class BookVO {
  @Data
  static class AddBook {
    /* 封面 */
    private String cover;

    /* 书名 */
    @NotEmpty(message = "书名不能为空")
    private String name;

    /* 简介 */
    @NotEmpty(message = "简介不能为空")
    private String desc;

    /* 作者 */
    @NotEmpty(message = "作者不能为空")
    private String auth;

    /* 出版社 */
    @NotEmpty(message = "出版社不能为空")
    private String publishingHouse;

    /* 类型 */
    private String type;

    /* 库存 */
    private int stock;

    /* 借阅数量 */
    private int borrowNum;

    /* 创建时间 */
    private Date create_time;

    /* 状态 1:上架 0:下架 */
    private int status;
  }

  @Data
  static class UpdateBook {
    @NotBlank(message = "id不能为空")
    private long id;
    /* 封面 */
    private String cover;

    /* 书名 */
    @NotEmpty(message = "书名不能为空")
    private String name;

    /* 简介 */
    @NotEmpty(message = "简介不能为空")
    private String desc;

    /* 作者 */
    @NotEmpty(message = "作者不能为空")
    private String auth;

    /* 出版社 */
    @NotEmpty(message = "出版社不能为空")
    private String publishingHouse;

    /* 类型 */
    private String type;

    /* 库存 */
    private int stock;

    /* 借阅数量 */
    private int borrowNum;

    /* 创建时间 */
    private Date create_time;

    /* 状态 1:上架 0:下架 */
    private int status;
  }

  @Data
  public static class QueryAll {
    @NotBlank(message = "pageNo不能为空")
    private int pageNo;

    private int pageSize;

    private String name;

    private String publishingHouse;

    private int status;

    private int stock;

    private int borrowNum;
  }
}
