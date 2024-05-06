package com.library.library.bean;

import java.util.List;

public class ListBean<T> {
  private List<T> list;

  private int total;

  public ListBean(List<T> list, int total) {
    this.list = list;
    this.total = total;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
