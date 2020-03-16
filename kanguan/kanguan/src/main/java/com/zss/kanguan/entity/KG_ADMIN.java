package com.zss.kanguan.entity;

import java.io.Serializable;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author zss
 * @since 2020-03-16
 */
public class KG_ADMIN implements Serializable {

    private static final long serialVersionUID=1L;

    private String ADMIN_ID;

    /**
     * 管理员名称
     */
    private String ADMIN_NAME;

    /**
     * 管理员密码
     */
    private String PASSWORD;

    private String CREATE_TIME;

    private String UPDATE_TIME;


    public String getADMIN_ID() {
        return ADMIN_ID;
    }

    public void setADMIN_ID(String ADMIN_ID) {
        this.ADMIN_ID = ADMIN_ID;
    }

    public String getADMIN_NAME() {
        return ADMIN_NAME;
    }

    public void setADMIN_NAME(String ADMIN_NAME) {
        this.ADMIN_NAME = ADMIN_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
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
        return "KG_ADMIN{" +
        "ADMIN_ID=" + ADMIN_ID +
        ", ADMIN_NAME=" + ADMIN_NAME +
        ", PASSWORD=" + PASSWORD +
        ", CREATE_TIME=" + CREATE_TIME +
        ", UPDATE_TIME=" + UPDATE_TIME +
        "}";
    }
}
