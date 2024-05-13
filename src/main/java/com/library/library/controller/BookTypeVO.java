package com.library.library.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class BookTypeVO {
  @Data
  public static class AddType {
    @NotBlank(message = "name不能为空")
    private String name;
  }

  @Data
  public static class AppTrackData {
    private String project;
    private String token;
    private String gzip;
    private String data_list;

  }

  @Data
  public static class DeleteType {
    @NotNull(message = "id不能为空")
    private long id;

  }

}
