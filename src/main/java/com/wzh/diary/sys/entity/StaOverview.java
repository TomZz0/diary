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
public class StaOverview implements Serializable {

    private static final long serialVersionUID = 1L;

    private String objCode;

    private String objName;

    private String staCode;

    private String staName;

    private String staType;

    private String staPro;

    private String pinyinCode;

    private String notes;

    public String getObjCode() {
        return objCode;
    }

    public void setObjCode(String objCode) {
        this.objCode = objCode;
    }
    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }
    public String getStaCode() {
        return staCode;
    }

    public void setStaCode(String staCode) {
        this.staCode = staCode;
    }
    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }
    public String getStaType() {
        return staType;
    }

    public void setStaType(String staType) {
        this.staType = staType;
    }
    public String getStaPro() {
        return staPro;
    }

    public void setStaPro(String staPro) {
        this.staPro = staPro;
    }
    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "StaOverview{" +
            "objCode=" + objCode +
            ", objName=" + objName +
            ", staCode=" + staCode +
            ", staName=" + staName +
            ", staType=" + staType +
            ", staPro=" + staPro +
            ", pinyinCode=" + pinyinCode +
            ", notes=" + notes +
        "}";
    }
}
