package com.neo.gw.model;

import com.neo.gw.controller.vm.DefaultRequestVM;
import com.neo.gw.handler.BasicChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public class Tps extends BasicChannelHandler implements Serializable {

    private Logger logger = LoggerFactory.getLogger(Tps.class);

    private long tps;

    public Tps() {
    }

    public Tps(long tps) {
        this.tps = tps;
    }

    public long getTps() {
        return tps;
    }

    public void setTps(long tps) {
        this.tps = tps;
    }

    @Override
    public void before(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("before Tps");
    }

    @Override
    public String execute(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("execute Tps");
        return "";
    }

    @Override
    public void after(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("after Tps");

        logger.debug("-----------------------------------------------------\n");
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Tps(getTps());
        }
    }
}
