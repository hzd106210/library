package com.library.library.controller;

import com.library.library.bean.PageBean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class ReaderVO {
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class AddReader {
    @NotEmpty(message = "name不能为空")
    private String name;

    @NotNull(message = "type不能为空")
    private int type;

    @NotEmpty(message = "contact不能为空")
    private String contact;

    @NotNull(message = "libraryId不能为空")
    private int libraryId;

    private long userId;

  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = false)
  public static class GetReaderList extends PageBean {
    private String name;
  }

  @Data
  public static class UpdateStatus {
    @NotNull(message = "id不能为空")
    private long id;

    @NotNull(message = "status不能为空")
    private int status;

  }
}
