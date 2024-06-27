package com.wzh.diary.sys.dto;

import com.wzh.diary.sys.entity.StaOverview;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 * @author wzh
 * @date 2024年01月11日 17:01
 * Description:
 */
@Data
public class StaOverviewDto extends StaOverview {
    private String staSort;
    private String address;
    private LocalDate buildTime;
    private String riverName;
}
