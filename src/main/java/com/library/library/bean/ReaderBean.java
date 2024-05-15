package com.library.library.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class ReaderBean {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private int type;

  private String contact;

  @Column(name = "borrow_card_number")
  private String borrowCardNumber;

  @Column(name = "library_id")
  private int libraryId;
}
