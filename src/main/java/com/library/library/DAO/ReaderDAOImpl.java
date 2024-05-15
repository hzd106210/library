package com.library.library.DAO;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.library.library.controller.ReaderVO.AddReader;

@Service("ReaderDAOImpl")
public class ReaderDAOImpl implements ReaderDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public boolean addReader(AddReader params) {
    String sql = "inser into reader (name,type,contact,borrow_card_number,library_id,user_id) values (?,?,?,?,?,?)";
    ArrayList<Object> arrayList = new ArrayList<>();
    arrayList.add(params.getName());
    arrayList.add(params.getType());
    arrayList.add(params.getContact());
    arrayList.add(params.getBorrowCardNumber());
    arrayList.add(params.getLibraryId());
    arrayList.add(params.getUserId());
    int update = jdbcTemplate.update(sql, arrayList.toArray());
    return update > 0;
  }

}
