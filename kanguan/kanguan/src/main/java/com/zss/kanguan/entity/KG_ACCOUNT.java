package com.zss.kanguan.entity;

import java.io.Serializable;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author zss
 * @since 2020-03-16
 */
public class KG_ACCOUNT implements Serializable {

    private static final long serialVersionUID=1L;

    private String ACCOUNT_ID;

    /**
     * 用户名
     */
    private String USERNAME;

    /**
     * 密码
     */
    private String PASSWORD;

    /**
     * 邮箱
     */
    private String EMAIL;

    private String CREATE_TIME;

    /**
     * 更新时间
     */
    private String UPDATE_TIME;


    public String getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(String ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
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
        return "KG_ACCOUNT{" +
        "ACCOUNT_ID=" + ACCOUNT_ID +
        ", USERNAME=" + USERNAME +
        ", PASSWORD=" + PASSWORD +
        ", EMAIL=" + EMAIL +
        ", CREATE_TIME=" + CREATE_TIME +
        ", UPDATE_TIME=" + UPDATE_TIME +
        "}";
    }
}
