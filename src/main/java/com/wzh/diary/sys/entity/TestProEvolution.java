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
public class TestProEvolution implements Serializable {

    private static final long serialVersionUID = 1L;
    private String staCode;

    private LocalDate year;

    private String proCode;

    private String notes;

    public String getStaCode() {
        return staCode;
    }

    public void setStaCode(String staCode) {
        this.staCode = staCode;
    }
    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }
    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "TestProEvolution{" +
            "staCode=" + staCode +
            ", year=" + year +
            ", proCode=" + proCode +
            ", notes=" + notes +
        "}";
    }
}
