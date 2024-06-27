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
public class StaLocation implements Serializable {
    public StaLocation(String objCode, String address) {
        this.objCode = objCode;
        this.address = address;
    }

    private static final long serialVersionUID = 1L;
    private String objCode;

    private String address;

    private BigDecimal eastLongitude;

    private BigDecimal northLatitude;

    private String notes;

    public String getObjCode() {
        return objCode;
    }

    public void setObjCode(String objCode) {
        this.objCode = objCode;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public BigDecimal getEastLongitude() {
        return eastLongitude;
    }

    public void setEastLongitude(BigDecimal eastLongitude) {
        this.eastLongitude = eastLongitude;
    }
    public BigDecimal getNorthLatitude() {
        return northLatitude;
    }

    public void setNorthLatitude(BigDecimal northLatitude) {
        this.northLatitude = northLatitude;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "StaLocation{" +
            "objCode=" + objCode +
            ", address=" + address +
            ", eastLongitude=" + eastLongitude +
            ", northLatitude=" + northLatitude +
            ", notes=" + notes +
        "}";
    }
}
