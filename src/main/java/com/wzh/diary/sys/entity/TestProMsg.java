package com.wzh.diary.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class TestProMsg implements Serializable {

    private static final long serialVersionUID = 1L;
    private String proCode;

    private String proName;

    private String notes;

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "TestProMsg{" +
            "proCode=" + proCode +
            ", proName=" + proName +
            ", notes=" + notes +
        "}";
    }
}
