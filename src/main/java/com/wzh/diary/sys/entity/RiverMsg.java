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
public class RiverMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String riverCode;

    private String riverName;

    private String riverSystem;

    private String basinName;

    private String notes;

    public String getRiverCode() {
        return riverCode;
    }

    public void setRiverCode(String riverCode) {
        this.riverCode = riverCode;
    }
    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }
    public String getRiverSystem() {
        return riverSystem;
    }

    public void setRiverSystem(String riverSystem) {
        this.riverSystem = riverSystem;
    }
    public String getBasinName() {
        return basinName;
    }

    public void setBasinName(String basinName) {
        this.basinName = basinName;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "RiverMsg{" +
            "riverCode=" + riverCode +
            ", riverName=" + riverName +
            ", riverSystem=" + riverSystem +
            ", basinName=" + basinName +
            ", notes=" + notes +
        "}";
    }
}
