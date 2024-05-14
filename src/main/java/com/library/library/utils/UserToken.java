package com.library.library.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

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

  public static Map<String, Claim> decodeToken(String token) {
    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(sign)).build();
    DecodedJWT verify = jwtVerifier.verify(token);
    return verify.getClaims();
  }
}
