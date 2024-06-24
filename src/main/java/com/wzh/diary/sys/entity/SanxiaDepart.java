package com.wzh.diary.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzh
 * @since 2024-01-09
 */
@Data
public class SanxiaDepart implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private String mgtDepart;

    private String pos;

    private String basinName;

    private String riverName;

    private String hydrology;

    private String evaporates;

    private String precipitation;

    private String waterQuality;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getMgtDepart() {
        return mgtDepart;
    }

    public void setMgtDepart(String mgtDepart) {
        this.mgtDepart = mgtDepart;
    }
    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
    public String getBasinName() {
        return basinName;
    }

    public void setBasinName(String basinName) {
        this.basinName = basinName;
    }
    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }
    public String getHydrology() {
        return hydrology;
    }

    public void setHydrology(String hydrology) {
        this.hydrology = hydrology;
    }
    public String getEvaporates() {
        return evaporates;
    }

    public void setEvaporates(String evaporates) {
        this.evaporates = evaporates;
    }
    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }
    public String getWaterQuality() {
        return waterQuality;
    }

    public void setWaterQuality(String waterQuality) {
        this.waterQuality = waterQuality;
    }

    @Override
    public String toString() {
        return "SanxiaDepart{" +
            "code=" + code +
            ", name=" + name +
            ", mgtDepart=" + mgtDepart +
            ", pos=" + pos +
            ", basinName=" + basinName +
            ", riverName=" + riverName +
            ", hydrology=" + hydrology +
            ", evaporates=" + evaporates +
            ", precipitation=" + precipitation +
            ", waterQuality=" + waterQuality +
        "}";
    }
}
