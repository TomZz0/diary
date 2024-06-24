package com.wzh.diary.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzh
 * @since 2024-01-09
 */
@Data
public class WaterLevel implements Serializable {

    private static final long serialVersionUID = 1L;
    private String staCode;

    private BigDecimal maxLevel;

    private LocalDate maxTime;

    private BigDecimal minLevel;

    private LocalDate minTime;

    private String observWay;

    private String msgStoreWay;

    public String getStaCode() {
        return staCode;
    }

    public void setStaCode(String staCode) {
        this.staCode = staCode;
    }
    public BigDecimal getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(BigDecimal maxLevel) {
        this.maxLevel = maxLevel;
    }
    public LocalDate getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(LocalDate maxTime) {
        this.maxTime = maxTime;
    }
    public BigDecimal getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(BigDecimal minLevel) {
        this.minLevel = minLevel;
    }
    public LocalDate getMinTime() {
        return minTime;
    }

    public void setMinTime(LocalDate minTime) {
        this.minTime = minTime;
    }
    public String getObservWay() {
        return observWay;
    }

    public void setObservWay(String observWay) {
        this.observWay = observWay;
    }
    public String getMsgStoreWay() {
        return msgStoreWay;
    }

    public void setMsgStoreWay(String msgStoreWay) {
        this.msgStoreWay = msgStoreWay;
    }

    @Override
    public String toString() {
        return "WaterLevel{" +
            "staCode=" + staCode +
            ", maxLevel=" + maxLevel +
            ", maxTime=" + maxTime +
            ", minLevel=" + minLevel +
            ", minTime=" + minTime +
            ", observWay=" + observWay +
            ", msgStoreWay=" + msgStoreWay +
        "}";
    }
}
