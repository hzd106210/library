/*
 * @Author: Huang Zhaoda 
 * @Date: 2024-05-05 22:56:55 
 * @Last Modified by: Huang Zhaoda
 * @Last Modified time: 2024-05-05 23:18:47
 * @Desc 同意处理参数校验后的错误信息
 */
package com.library.library;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.library.library.controller.ResponseResult;

@RestControllerAdvice
public class ExceptionHandlers {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseResult<String> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    System.err.println(e.toString());
    String errMsg = e.getBindingResult().getFieldErrors().stream()
        .map(f -> f.getDefaultMessage())
        .collect(Collectors.joining("| "));
    return ResponseResult.fail(null, errMsg);
  }

  // 全局处理没有请求体抛出的错误
  // @ExceptionHandler(HttpMessageNotReadableException.class)
  // @ResponseBody
  // public ResponseEntity<String>
  // handleHttpMessageNotReadableException(HttpServletRequest request,
  // HttpMessageNotReadableException ex) {
  // System.err.println(request.getMethod());
  // if (request.getMethod().equals(RequestMethod.POST.name())) {
  // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body is
  // missing");
  // } else {
  // return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
  // .body("Unsupported HTTP method: " + request.getMethod());
  // }
  // }
}
