package com.neo.gw.handler;

import com.neo.gw.controller.vm.DefaultRequestVM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public abstract class BasicChannelHandler implements ChannelHandler<BasicChannelHandler> {

  @Override
  public void before(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {

  }

  @Override
  public String execute(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
    return null;
  }

  @Override
  public void after(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {

  }


}
