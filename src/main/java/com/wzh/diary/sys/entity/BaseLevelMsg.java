package com.wzh.diary.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzh
 * @since 2024-01-09
 */
@Data
public class BaseLevelMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String baseLevelCode;

    private String baseLevelName;

    private BigDecimal baseLevelElevation;

    private BigDecimal baseLevelCorrValue;

    private String notes;

    public String getBaseLevelCode() {
        return baseLevelCode;
    }

    public void setBaseLevelCode(String baseLevelCode) {
        this.baseLevelCode = baseLevelCode;
    }
    public String getBaseLevelName() {
        return baseLevelName;
    }

    public void setBaseLevelName(String baseLevelName) {
        this.baseLevelName = baseLevelName;
    }
    public BigDecimal getBaseLevelElevation() {
        return baseLevelElevation;
    }

    public void setBaseLevelElevation(BigDecimal baseLevelElevation) {
        this.baseLevelElevation = baseLevelElevation;
    }
    public BigDecimal getBaseLevelCorrValue() {
        return baseLevelCorrValue;
    }

    public void setBaseLevelCorrValue(BigDecimal baseLevelCorrValue) {
        this.baseLevelCorrValue = baseLevelCorrValue;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "BaseLevelMsg{" +
            "baseLevelCode=" + baseLevelCode +
            ", baseLevelName=" + baseLevelName +
            ", baseLevelElevation=" + baseLevelElevation +
            ", baseLevelCorrValue=" + baseLevelCorrValue +
            ", notes=" + notes +
        "}";
    }
}
