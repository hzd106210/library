package com.library.library.bean;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageBean {
  @NotNull(message = "pageNo不能为空")
  private int pageNo;

  private Integer pageSize;
}
