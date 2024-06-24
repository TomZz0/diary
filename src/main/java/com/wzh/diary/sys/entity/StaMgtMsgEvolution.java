package com.wzh.diary.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
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
public class StaMgtMsgEvolution implements Serializable {

    private static final long serialVersionUID = 1L;
    private String objCode;

    private LocalDate startTime;

    private LocalDate endTime;

    private String riverCode;

    private String mgtWay;

    private String mgtDepartment;

    private String notes;

    public String getObjCode() {
        return objCode;
    }

    public void setObjCode(String objCode) {
        this.objCode = objCode;
    }
    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }
    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }
    public String getRiverCode() {
        return riverCode;
    }

    public void setRiverCode(String riverCode) {
        this.riverCode = riverCode;
    }
    public String getMgtWay() {
        return mgtWay;
    }

    public void setMgtWay(String mgtWay) {
        this.mgtWay = mgtWay;
    }
    public String getMgtDepartment() {
        return mgtDepartment;
    }

    public void setMgtDepartment(String mgtDepartment) {
        this.mgtDepartment = mgtDepartment;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "StaMgtMsgEvolution{" +
            "objCode=" + objCode +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", riverCode=" + riverCode +
            ", mgtWay=" + mgtWay +
            ", mgtDepartment=" + mgtDepartment +
            ", notes=" + notes +
        "}";
    }
}
