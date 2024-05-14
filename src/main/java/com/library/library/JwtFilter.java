package com.library.library;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.library.DAO.UserTokenDaoImpl;
import com.library.library.controller.ResponseResult;
import com.library.library.utils.UserToken;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(filterName = "JwtFilter", urlPatterns = { "/book/*", "/bookType/*" })
public class JwtFilter implements Filter {
  public static org.slf4j.Logger getLog() {
    return log;
  }

  @Autowired
  private UserTokenDaoImpl userTokenDaoImpl;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    final HttpServletRequest req = (HttpServletRequest) request;
    final HttpServletResponse res = (HttpServletResponse) response;
    ObjectMapper objectMapper = new ObjectMapper();

    res.setContentType("text/html; charset=UTF-8");
    res.setCharacterEncoding("UTF-8");

    String token = req.getHeader("token");

    if (req.getMethod().equals("OPTIONS")) {
      res.setStatus(HttpServletResponse.SC_OK);
      chain.doFilter(req, res);
    } else {
      if (token == null) {
        res.getWriter().write(objectMapper.writeValueAsString(ResponseResult.fail("请登录")));
        return;
      }
      Map<String, Claim> decodeToken = UserToken.decodeToken(token);
      if (decodeToken == null) {
        res.getWriter().write(objectMapper.writeValueAsString(ResponseResult.fail("token不合法")));
        return;
      }
      String userId = decodeToken.get("userId").asString();
      String token2 = userTokenDaoImpl.findByUserId(userId);
      if (!token2.equals(token)) {
        res.getWriter().write(objectMapper.writeValueAsString(ResponseResult.fail("请重新登录")));
        return;
      }

      chain.doFilter(req, res);
    }
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

}
