package com.neo.gw.model;

import com.neo.gw.controller.vm.DefaultRequestVM;
import com.neo.gw.exception.RequestInvalidException;
import com.neo.gw.handler.BasicChannelHandler;
import com.neo.gw.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public class Ip extends BasicChannelHandler implements Serializable {

    private Logger logger = LoggerFactory.getLogger(Ip.class);

    private String ip;

    public Ip() {
    }

    public Ip(String ip) {
        this.ip = ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public void before(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("before Ip");
    }

    @Override
    public String execute(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("execute Ip");

        logger.debug("ip hashMap: {}", this.ip);
        logger.debug("ip input: {}", defaultRequestVM.getAuthen().get(Constant.IP_KEY));

        if (!this.ip.equals(defaultRequestVM.getAuthen().get(Constant.IP_KEY))) {
            throw new RequestInvalidException("Địa chỉ Ip không hợp lệ");
        }
        return this.ip;
    }

    @Override
    public void after(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("after Ip");

        logger.debug("-----------------------------------------------------\n");
    }

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Ip(getIp());
        }
    }
}
