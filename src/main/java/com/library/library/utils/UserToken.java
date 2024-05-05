package com.library.library.utils;

import java.util.Date;
import java.util.HashMap;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class UserToken {
  private static final String sign = "__library_sign__";

  public static String getToken(String userId) {
    try {
      Algorithm hmac256 = Algorithm.HMAC256(sign);
      String token = JWT.create().withHeader(new HashMap<>())
          .withClaim("userId", userId)
          .withExpiresAt(new Date(Long.MAX_VALUE))
          .sign(hmac256);
      return token;
    } catch (Exception e) {
      return null;
    }
  }
}
