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
public class StaBasicMsg implements Serializable {
    public StaBasicMsg(String staCode) {
        this.staCode = staCode;
    }

    private static final long serialVersionUID = 1L;
    private String staCode;

    private String staType;

    private String staPro;

    private String baseLevelCode;

    private LocalDate buildTime;

    private String buildDepartment;

    private String surveyTeam;

    private LocalDate removeTime;

    private String notes;

    public String getStaCode() {
        return staCode;
    }

    public void setStaCode(String staCode) {
        this.staCode = staCode;
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
    public String getBaseLevelCode() {
        return baseLevelCode;
    }

    public void setBaseLevelCode(String baseLevelCode) {
        this.baseLevelCode = baseLevelCode;
    }
    public LocalDate getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(LocalDate buildTime) {
        this.buildTime = buildTime;
    }
    public String getBuildDepartment() {
        return buildDepartment;
    }

    public void setBuildDepartment(String buildDepartment) {
        this.buildDepartment = buildDepartment;
    }
    public String getSurveyTeam() {
        return surveyTeam;
    }

    public void setSurveyTeam(String surveyTeam) {
        this.surveyTeam = surveyTeam;
    }
    public LocalDate getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(LocalDate removeTime) {
        this.removeTime = removeTime;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "StaBasicMsg{" +
            "staCode=" + staCode +
            ", staType=" + staType +
            ", staPro=" + staPro +
            ", baseLevelCode=" + baseLevelCode +
            ", buildTime=" + buildTime +
            ", buildDepartment=" + buildDepartment +
            ", surveyTeam=" + surveyTeam +
            ", removeTime=" + removeTime +
            ", notes=" + notes +
        "}";
    }
}
