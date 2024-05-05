package com.library.library.controller;

import java.io.Serializable;

import jakarta.validation.constraints.Null;

public class ResponseResult<T> implements Serializable {
  /**
   * 状态值
   */
  private int code;

  /**
   * 返回数据
   */
  private T data;

  /**
   * 消息
   */
  private String message;

  public ResponseResult(int code, T _data, String message) {
    this.code = code;
    this.data = _data;
    this.message = message;
  }

  /*
   * 成功
   */
  public static <T> ResponseResult<T> success(T Data, String message) {
    return new ResponseResult<T>(ResultCode.SUCCESS, Data, message);
  }

  /*
   * 失败
   */
  public static <T> ResponseResult<T> fail(T data, String message) {
    return new ResponseResult<T>(ResultCode.SUCCESS, data, message);
  }

  public static <T> ResponseResult<T> fail(String message) {
    return new ResponseResult<T>(ResultCode.SUCCESS, null, message);
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
