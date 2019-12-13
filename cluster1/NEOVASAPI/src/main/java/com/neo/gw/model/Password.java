package com.neo.gw.model;

import com.neo.gw.controller.vm.DefaultRequestVM;
import com.neo.gw.exception.RequestInvalidException;
import com.neo.gw.handler.BasicChannelHandler;
import com.neo.gw.util.Constant;
import org.apache.http.impl.execchain.RequestAbortedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password extends BasicChannelHandler implements Serializable {

  private Logger logger = LoggerFactory.getLogger(Password.class);

  private String password;

  public Password() {}

  public Password(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public void before(
      DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
    logger.debug("before Password");
  }

  @Override
  public String execute(
      DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
    logger.debug("execute Password");

    logger.debug("password hashMap: {}", this.password);

    String passwordInput = defaultRequestVM.getAuthen().get(Constant.PASSWORD_KEY);
    logger.debug("password input: {}", passwordInput);

    String hashPassword = null;

    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(passwordInput.getBytes());

      byte[] digest = md5.digest();
      hashPassword = DatatypeConverter.printHexBinary(digest);

      logger.debug("hash Password: {}", hashPassword.toLowerCase());

      if (!this.password.equals(hashPassword.toLowerCase())) {
        throw new RequestInvalidException("Mật khẩu không hợp lệ");
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new RequestInvalidException("Mật khẩu không hợp lệ");
    }
    return this.password;
  }

  @Override
  public void after(
      DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
    logger.debug("after Password");

    logger.debug("-----------------------------------------------------\n");
  }
}
