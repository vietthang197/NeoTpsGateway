package com.neo.gw.model;

import com.neo.gw.controller.vm.DefaultRequestVM;
import com.neo.gw.handler.BasicChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public class Username extends BasicChannelHandler implements Serializable {

    private Logger logger = LoggerFactory.getLogger(Username.class);

    private String username;

    public Username() {
    }

    public Username(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void before(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("before Username");
    }

    @Override
    public String execute(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("execute Username");

        return this.username;
    }

    @Override
    public void after(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("after Username");

        logger.debug("-----------------------------------------------------\n");
    }
}
