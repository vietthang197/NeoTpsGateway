package com.neo.gw.services;

import com.neo.gw.controller.vm.DefaultRequestVM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PipelineChannel<T> {
    String execute(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response);
}
