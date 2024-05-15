package com.library.library.controller;

import com.library.library.bean.PageBean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class LIbraryVO {
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = false)
  public static class FindAllLibrary extends PageBean {
    private String name;
  }

  @Data
  public static class AddLibrary {
    @NotEmpty(message = "name不能为空")
    private String name;

    @NotEmpty(message = "address不能为空")
    private String address;

    @NotEmpty(message = "contact不能为空")
    private String contact;

    @NotEmpty(message = "contactPerson不能为空")
    private String contactPerson;

  }

  @Data
  public static class DeleteLibrary {
    @NotNull(message = "id不能为空")
    private long id;

  }
}
