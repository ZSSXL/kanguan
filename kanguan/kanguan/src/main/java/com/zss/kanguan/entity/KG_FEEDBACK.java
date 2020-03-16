package com.zss.kanguan.entity;

import java.io.Serializable;

/**
 * <p>
 * 反馈表
 * </p>
 *
 * @author zss
 * @since 2020-03-16
 */
public class KG_FEEDBACK implements Serializable {

    private static final long serialVersionUID=1L;

    private String FEEDBACK_ID;

    /**
     * 反馈内容
     */
    private String CONTENT;

    private String CREATE_TIME;

    private String UPDATE_TIME;


    public String getFEEDBACK_ID() {
        return FEEDBACK_ID;
    }

    public void setFEEDBACK_ID(String FEEDBACK_ID) {
        this.FEEDBACK_ID = FEEDBACK_ID;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
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
        return "KG_FEEDBACK{" +
        "FEEDBACK_ID=" + FEEDBACK_ID +
        ", CONTENT=" + CONTENT +
        ", CREATE_TIME=" + CREATE_TIME +
        ", UPDATE_TIME=" + UPDATE_TIME +
        "}";
    }
}
