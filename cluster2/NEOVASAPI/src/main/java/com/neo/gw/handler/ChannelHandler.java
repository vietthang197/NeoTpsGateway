package com.neo.gw.handler;

import com.neo.gw.controller.vm.DefaultRequestVM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

/**
 * *
 *
 * @param <T>
 */
public interface ChannelHandler<T> {
  /** @return T */
  void before(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response);

  /** @return T */
  String execute(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException;

  /** @return T */
  void after(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response);
}
