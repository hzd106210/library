package com.library.library.controller;

import com.library.library.bean.PageBean;

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
}
