package com.zss.kanguan.entity;

import java.io.Serializable;

/**
 * <p>
 * 订阅表
 * </p>
 *
 * @author zss
 * @since 2020-03-16
 */
public class KG_SUBSCRIPTION implements Serializable {

    private static final long serialVersionUID=1L;

    private String SUB_ID;

    /**
     * 订阅名称
     */
    private String NAME;

    private String CREATE_TIME;

    private String UPDATE_TIME;


    public String getSUB_ID() {
        return SUB_ID;
    }

    public void setSUB_ID(String SUB_ID) {
        this.SUB_ID = SUB_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
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
        return "KG_SUBSCRIPTION{" +
        "SUB_ID=" + SUB_ID +
        ", NAME=" + NAME +
        ", CREATE_TIME=" + CREATE_TIME +
        ", UPDATE_TIME=" + UPDATE_TIME +
        "}";
    }
}
