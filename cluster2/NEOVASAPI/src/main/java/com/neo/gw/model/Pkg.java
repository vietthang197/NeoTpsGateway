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

public class Pkg extends BasicChannelHandler implements Serializable {

    private Logger logger = LoggerFactory.getLogger(Pkg.class);

    private String pkgCode;

    public Pkg() {
    }

    public Pkg(String pkgCode) {
        this.pkgCode = pkgCode;
    }

    public String getPkgCode() {
        return pkgCode;
    }

    public void setPkgCode(String pkgCode) {
        this.pkgCode = pkgCode;
    }

    @Override
    public void before(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("before Pkg");
    }

    @Override
    public String execute(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("execute Pkg");

        logger.debug("pkgCode hashMap: {}", this.pkgCode);
        logger.debug("pkgCode input: {}", defaultRequestVM.getRequest().get(Constant.PKG_CODE_KEY));

        if (!this.pkgCode.equals(defaultRequestVM.getRequest().get(Constant.PKG_CODE_KEY))) {
            throw new RequestInvalidException("pkg_code không hợp lệ");
        }

        return this.pkgCode;
    }

    @Override
    public void after(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("after Pkg");

        logger.debug("-----------------------------------------------------\n");
    }
}
