package com.library.library.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
public class LoanBean {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "reader_id")
  private long readerId;

  @Column(name = "book_id")
  private long bookId;

  @Column(name = "borrow_time")
  private Date borrowTime;

  @Column(name = "back_time")
  private Date backTime;

  @Column(name = "reader_name")
  private String readerName;

  @Column(name = "book_name")
  private String bookName;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = false)
  public static class GetListParams extends PageBean {
    private String readerName;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = false)
  public static class AddLoanParams {
    @NotNull(message = "readerId不能为空")
    private long readerId;

    @NotNull(message = "bookId不能为空")
    private long bookId;

    @NotNull(message = "borrowTime不能为空")
    private Date borrowTime;
  }
}
