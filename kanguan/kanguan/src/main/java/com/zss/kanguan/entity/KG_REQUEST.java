package com.zss.kanguan.entity;

import java.io.Serializable;

/**
 * <p>
 * 求资源
 * </p>
 *
 * @author zss
 * @since 2020-03-16
 */
public class KG_REQUEST implements Serializable {

    private static final long serialVersionUID=1L;

    private String REQUEST_ID;

    /**
     * 豆瓣地址
     */
    private String DOUBAN_ADDRESS;

    /**
     * 资源名称
     */
    private String NAME;

    /**
     * 是否已收录，1 已收录，0 未收录
     */
    private String EXIST;

    private String CREATE_TIME;

    private String UPDATE_TIME;


    public String getREQUEST_ID() {
        return REQUEST_ID;
    }

    public void setREQUEST_ID(String REQUEST_ID) {
        this.REQUEST_ID = REQUEST_ID;
    }

    public String getDOUBAN_ADDRESS() {
        return DOUBAN_ADDRESS;
    }

    public void setDOUBAN_ADDRESS(String DOUBAN_ADDRESS) {
        this.DOUBAN_ADDRESS = DOUBAN_ADDRESS;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEXIST() {
        return EXIST;
    }

    public void setEXIST(String EXIST) {
        this.EXIST = EXIST;
    }

    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(String UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    @Override
    public String toString() {
        return "KG_REQUEST{" +
        "REQUEST_ID=" + REQUEST_ID +
        ", DOUBAN_ADDRESS=" + DOUBAN_ADDRESS +
        ", NAME=" + NAME +
        ", EXIST=" + EXIST +
        ", CREATE_TIME=" + CREATE_TIME +
        ", UPDATE_TIME=" + UPDATE_TIME +
        "}";
    }
}
