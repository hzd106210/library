package com.library.library.utils;

public class SQL {
  public static String insertSqlWhereAnd(String sql, String str) {
    if (sql.indexOf("where") > -1) {
      sql += " and";
    } else {
      sql += " where";
    }
    sql += str;
    return sql;
  }
}
