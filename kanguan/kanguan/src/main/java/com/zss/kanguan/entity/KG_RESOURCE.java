package com.zss.kanguan.entity;

import java.io.Serializable;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author zss
 * @since 2020-03-16
 */
public class KG_RESOURCE implements Serializable {

    private static final long serialVersionUID=1L;

    private String RESOURCE_ID;

    /**
     * 资源名称
     */
    private String NAME;

    /**
     * 集数
     */
    private String EPISODE;

    /**
     * 大小
     */
    private String BIGNESS;

    /**
     * 格式
     */
    private String FORMAT;

    /**
     * 资源链接
     */
    private String DOWNLOAD_LINK;

    private String CREATE_TIME;

    private String UPDATE_TIME;


    public String getRESOURCE_ID() {
        return RESOURCE_ID;
    }

    public void setRESOURCE_ID(String RESOURCE_ID) {
        this.RESOURCE_ID = RESOURCE_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEPISODE() {
        return EPISODE;
    }

    public void setEPISODE(String EPISODE) {
        this.EPISODE = EPISODE;
    }

    public String getBIGNESS() {
        return BIGNESS;
    }

    public void setBIGNESS(String BIGNESS) {
        this.BIGNESS = BIGNESS;
    }

    public String getFORMAT() {
        return FORMAT;
    }

    public void setFORMAT(String FORMAT) {
        this.FORMAT = FORMAT;
    }

    public String getDOWNLOAD_LINK() {
        return DOWNLOAD_LINK;
    }

    public void setDOWNLOAD_LINK(String DOWNLOAD_LINK) {
        this.DOWNLOAD_LINK = DOWNLOAD_LINK;
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
        return "KG_RESOURCE{" +
        "RESOURCE_ID=" + RESOURCE_ID +
        ", NAME=" + NAME +
        ", EPISODE=" + EPISODE +
        ", BIGNESS=" + BIGNESS +
        ", FORMAT=" + FORMAT +
        ", DOWNLOAD_LINK=" + DOWNLOAD_LINK +
        ", CREATE_TIME=" + CREATE_TIME +
        ", UPDATE_TIME=" + UPDATE_TIME +
        "}";
    }
}
