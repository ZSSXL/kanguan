package com.zss.kanguan.entity;

import java.io.Serializable;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author zss
 * @since 2020-03-16
 */
public class KG_COMMENT implements Serializable {

    private static final long serialVersionUID=1L;

    private String COMMENT_ID;

    /**
     * 评论人
     */
    private String COMMENTATOR;

    /**
     * 评论内容
     */
    private String CONTENT;

    /**
     * 评论的对象
     */
    private String OBJECT;

    private String CREATE_TIME;

    private String UPDATE_TIME;


    public String getCOMMENT_ID() {
        return COMMENT_ID;
    }

    public void setCOMMENT_ID(String COMMENT_ID) {
        this.COMMENT_ID = COMMENT_ID;
    }

    public String getCOMMENTATOR() {
        return COMMENTATOR;
    }

    public void setCOMMENTATOR(String COMMENTATOR) {
        this.COMMENTATOR = COMMENTATOR;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getOBJECT() {
        return OBJECT;
    }

    public void setOBJECT(String OBJECT) {
        this.OBJECT = OBJECT;
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
        return "KG_COMMENT{" +
        "COMMENT_ID=" + COMMENT_ID +
        ", COMMENTATOR=" + COMMENTATOR +
        ", CONTENT=" + CONTENT +
        ", OBJECT=" + OBJECT +
        ", CREATE_TIME=" + CREATE_TIME +
        ", UPDATE_TIME=" + UPDATE_TIME +
        "}";
    }
}
