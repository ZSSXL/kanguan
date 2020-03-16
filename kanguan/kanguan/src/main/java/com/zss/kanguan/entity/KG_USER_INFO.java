package com.zss.kanguan.entity;

import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author zss
 * @since 2020-03-16
 */
public class KG_USER_INFO implements Serializable {

    private static final long serialVersionUID=1L;

    private String INFO_ID;

    /**
     * 是否会员
     */
    private String MEMBER;

    /**
     * 最近登录
     */
    private String RECENTLY_LOGIN;

    private String CREATE_TIME;

    private String UPDATE_TIME;


    public String getINFO_ID() {
        return INFO_ID;
    }

    public void setINFO_ID(String INFO_ID) {
        this.INFO_ID = INFO_ID;
    }

    public String getMEMBER() {
        return MEMBER;
    }

    public void setMEMBER(String MEMBER) {
        this.MEMBER = MEMBER;
    }

    public String getRECENTLY_LOGIN() {
        return RECENTLY_LOGIN;
    }

    public void setRECENTLY_LOGIN(String RECENTLY_LOGIN) {
        this.RECENTLY_LOGIN = RECENTLY_LOGIN;
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
        return "KG_USER_INFO{" +
        "INFO_ID=" + INFO_ID +
        ", MEMBER=" + MEMBER +
        ", RECENTLY_LOGIN=" + RECENTLY_LOGIN +
        ", CREATE_TIME=" + CREATE_TIME +
        ", UPDATE_TIME=" + UPDATE_TIME +
        "}";
    }
}
