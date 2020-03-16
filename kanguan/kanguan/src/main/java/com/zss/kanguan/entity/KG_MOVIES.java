package com.zss.kanguan.entity;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * <p>
 * 影视剧信息表
 * </p>
 *
 * @author zss
 * @since 2020-03-16
 */
public class KG_MOVIES implements Serializable {

    private static final long serialVersionUID=1L;

    private String MOVIE_ID;

    /**
     * 名称
     */
    private String NAME;

    /**
     * 封面
     */
    private String COVER;

    /**
     * 导演
     */
    private String DIRECTOR;

    /**
     * 编剧
     */
    private String SENARIST;

    /**
     * 主演
     */
    private String STARING;

    /**
     * 类型
     */
    private String STYLE;

    /**
     * 电影/电视剧，1 是电影，0 是电视剧
     */
    private BigDecimal TYPE;

    /**
     * 国家/地区
     */
    private String COUNTRY_REGIN;

    /**
     * 首映时间
     */
    private String PREMIERE;

    /**
     * 上映时间
     */
    private String RELEASE;

    /**
     * 片长
     */
    private String LENGTH;

    /**
     * 又名
     */
    private String ANOTHER_NAME;

    /**
     * 评分
     */
    private BigDecimal SCORE;

    /**
     * 简介
     */
    private String INTRODUCTION;

    private String CREATE_TIME;

    private String UPDATE_TIME;


    public String getMOVIE_ID() {
        return MOVIE_ID;
    }

    public void setMOVIE_ID(String MOVIE_ID) {
        this.MOVIE_ID = MOVIE_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCOVER() {
        return COVER;
    }

    public void setCOVER(String COVER) {
        this.COVER = COVER;
    }

    public String getDIRECTOR() {
        return DIRECTOR;
    }

    public void setDIRECTOR(String DIRECTOR) {
        this.DIRECTOR = DIRECTOR;
    }

    public String getSENARIST() {
        return SENARIST;
    }

    public void setSENARIST(String SENARIST) {
        this.SENARIST = SENARIST;
    }

    public String getSTARING() {
        return STARING;
    }

    public void setSTARING(String STARING) {
        this.STARING = STARING;
    }

    public String getSTYLE() {
        return STYLE;
    }

    public void setSTYLE(String STYLE) {
        this.STYLE = STYLE;
    }

    public BigDecimal getTYPE() {
        return TYPE;
    }

    public void setTYPE(BigDecimal TYPE) {
        this.TYPE = TYPE;
    }

    public String getCOUNTRY_REGIN() {
        return COUNTRY_REGIN;
    }

    public void setCOUNTRY_REGIN(String COUNTRY_REGIN) {
        this.COUNTRY_REGIN = COUNTRY_REGIN;
    }

    public String getPREMIERE() {
        return PREMIERE;
    }

    public void setPREMIERE(String PREMIERE) {
        this.PREMIERE = PREMIERE;
    }

    public String getRELEASE() {
        return RELEASE;
    }

    public void setRELEASE(String RELEASE) {
        this.RELEASE = RELEASE;
    }

    public String getLENGTH() {
        return LENGTH;
    }

    public void setLENGTH(String LENGTH) {
        this.LENGTH = LENGTH;
    }

    public String getANOTHER_NAME() {
        return ANOTHER_NAME;
    }

    public void setANOTHER_NAME(String ANOTHER_NAME) {
        this.ANOTHER_NAME = ANOTHER_NAME;
    }

    public BigDecimal getSCORE() {
        return SCORE;
    }

    public void setSCORE(BigDecimal SCORE) {
        this.SCORE = SCORE;
    }

    public String getINTRODUCTION() {
        return INTRODUCTION;
    }

    public void setINTRODUCTION(String INTRODUCTION) {
        this.INTRODUCTION = INTRODUCTION;
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
        return "KG_MOVIES{" +
        "MOVIE_ID=" + MOVIE_ID +
        ", NAME=" + NAME +
        ", COVER=" + COVER +
        ", DIRECTOR=" + DIRECTOR +
        ", SENARIST=" + SENARIST +
        ", STARING=" + STARING +
        ", STYLE=" + STYLE +
        ", TYPE=" + TYPE +
        ", COUNTRY_REGIN=" + COUNTRY_REGIN +
        ", PREMIERE=" + PREMIERE +
        ", RELEASE=" + RELEASE +
        ", LENGTH=" + LENGTH +
        ", ANOTHER_NAME=" + ANOTHER_NAME +
        ", SCORE=" + SCORE +
        ", INTRODUCTION=" + INTRODUCTION +
        ", CREATE_TIME=" + CREATE_TIME +
        ", UPDATE_TIME=" + UPDATE_TIME +
        "}";
    }
}
