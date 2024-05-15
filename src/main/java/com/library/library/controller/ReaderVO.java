package com.library.library.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class ReaderVO {
  @Data
  public static class AddReader {
    @NotEmpty(message = "name不能为空")
    private String name;

    @NotNull(message = "type不能为空")
    private int type;

    @NotEmpty(message = "contact不能为空")
    private String contact;

    @NotEmpty(message = "borrowCardNumber不能为空")
    private String borrowCardNumber;

    @NotNull(message = "libraryId不能为空")
    private int libraryId;

    @NotNull(message = "userId不能为空")
    private long userId;

  }
}
