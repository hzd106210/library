package com.library.library.controller;

public interface ResultCode {
  /*
   * 成功
   */
  int SUCCESS = 200;

  /*
   * 系统错误
   */
  int SYSTEM_ERROR = 500;

  /**
   * 无权限
   */
  int FOR_BIDDEN = 403;
}
