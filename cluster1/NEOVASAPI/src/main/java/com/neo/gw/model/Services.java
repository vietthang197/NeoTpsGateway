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

public class Services extends BasicChannelHandler implements Serializable {

    private Logger logger = LoggerFactory.getLogger(Services.class);

    private String serviceCode;

    public Services() {
    }

    public Services(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Override
    public void before(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("before Services");
    }

    @Override
    public String execute(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("execute Services");

        logger.debug("serviceCode hashMap: {}", this.serviceCode);
        logger.debug("serviceCode input: {}", defaultRequestVM.getRequest().get("service_code"));

        if (!this.serviceCode.equals(defaultRequestVM.getRequest().get(Constant.SERVICE_CODE_KEY))) {
            throw new RequestInvalidException("service_code không hợp lệ");
        }

        return this.serviceCode;
    }

    @Override
    public void after(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("after Services");

        logger.debug("-----------------------------------------------------\n");
    }
}
