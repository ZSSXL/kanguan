package com.zss.kanguan.entity;

import java.io.Serializable;

/**
 * <p>
 * 字母表
 * </p>
 *
 * @author zss
 * @since 2020-03-16
 */
public class KG_SUBTITLE implements Serializable {

    private static final long serialVersionUID=1L;

    private String SUBTITLE_ID;

    /**
     * 字幕名称
     */
    private String NAME;

    /**
     * 语言
     */
    private String LANGUAGE;

    /**
     * 格式
     */
    private String FORMAT;

    /**
     * 集数
     */
    private String EPISODE;

    /**
     * 下载地址
     */
    private String DOWNLOAD;

    private String CREATE_TIME;

    private String UPDATE_TIME;


    public String getSUBTITLE_ID() {
        return SUBTITLE_ID;
    }

    public void setSUBTITLE_ID(String SUBTITLE_ID) {
        this.SUBTITLE_ID = SUBTITLE_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getLANGUAGE() {
        return LANGUAGE;
    }

    public void setLANGUAGE(String LANGUAGE) {
        this.LANGUAGE = LANGUAGE;
    }

    public String getFORMAT() {
        return FORMAT;
    }

    public void setFORMAT(String FORMAT) {
        this.FORMAT = FORMAT;
    }

    public String getEPISODE() {
        return EPISODE;
    }

    public void setEPISODE(String EPISODE) {
        this.EPISODE = EPISODE;
    }

    public String getDOWNLOAD() {
        return DOWNLOAD;
    }

    public void setDOWNLOAD(String DOWNLOAD) {
        this.DOWNLOAD = DOWNLOAD;
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
        return "KG_SUBTITLE{" +
        "SUBTITLE_ID=" + SUBTITLE_ID +
        ", NAME=" + NAME +
        ", LANGUAGE=" + LANGUAGE +
        ", FORMAT=" + FORMAT +
        ", EPISODE=" + EPISODE +
        ", DOWNLOAD=" + DOWNLOAD +
        ", CREATE_TIME=" + CREATE_TIME +
        ", UPDATE_TIME=" + UPDATE_TIME +
        "}";
    }
}
