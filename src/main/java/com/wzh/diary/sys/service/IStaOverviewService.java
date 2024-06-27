package com.wzh.diary.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzh.diary.sys.dto.StaOverviewDto;
import com.wzh.diary.sys.entity.R;
import com.wzh.diary.sys.entity.StaOverview;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzh
 * @since 2024-01-09
 */
public interface IStaOverviewService extends IService<StaOverview> {

    R<String> addSta(StaOverviewDto staOverviewDto);

    R<String> modifySta(StaOverviewDto staOverviewDto);

    R<String> delete(String staCode);

}
