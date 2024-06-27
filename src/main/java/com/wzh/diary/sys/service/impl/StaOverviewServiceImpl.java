package com.wzh.diary.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzh.diary.sys.dto.StaOverviewDto;
import com.wzh.diary.sys.entity.*;
import com.wzh.diary.sys.mapper.StaOverviewMapper;
import com.wzh.diary.sys.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzh
 * @since 2024-01-09
 */
@Service
public class StaOverviewServiceImpl extends ServiceImpl<StaOverviewMapper, StaOverview> implements IStaOverviewService {
    @Autowired
    private IRiverMsgService riverMsgService;

    @Autowired
    private IStaMgtMsgEvolutionService mgtMsgEvolutionService;

    @Autowired
    private ITestProMsgService testProMsgService;
    @Autowired
    private ITestProEvolutionService testProEvolutionService;

    @Autowired
    private IStaLocationService staLocationService;

    @Autowired
    private IStaBasicMsgService basicMsgService;
    @Override
    @Transactional
    public R<String> addSta(StaOverviewDto staOverviewDto) {

        LambdaQueryWrapper<StaOverview> staOverviewLambdaQueryWrapper = new LambdaQueryWrapper<>();
        staOverviewLambdaQueryWrapper.eq(StaOverview::getStaCode,staOverviewDto.getStaCode());
        staOverviewLambdaQueryWrapper.eq(StaOverview::getObjCode,staOverviewDto.getObjCode());
        StaOverview one = getOne(staOverviewLambdaQueryWrapper);
        if (one != null) return R.error("测站编码或对象编码已存在");
        //增加河流
        String riverName = staOverviewDto.getRiverName();
        LambdaQueryWrapper<RiverMsg> riverMsgLambdaQueryWrapper = new LambdaQueryWrapper<>();
        riverMsgLambdaQueryWrapper.eq(RiverMsg::getRiverName,riverName);
        RiverMsg river = riverMsgService.getOne(riverMsgLambdaQueryWrapper);
        if (river == null){
            river = new RiverMsg(riverName,"F0","F");
            List<RiverMsg> list = riverMsgService.list();
            List<RiverMsg> collect = list.stream().sorted((x,y)-> Integer.parseInt(y.getRiverCode()) - Integer.parseInt(x.getRiverCode())).limit(1).collect(Collectors.toList());
            river.setRiverCode(Integer.parseInt(collect.get(0).getRiverCode()) + 1 + "");
            riverMsgService.save(river);
        }
        //增加测站信息管理沿革表
        StaMgtMsgEvolution mgtMsgEvolution = new StaMgtMsgEvolution(staOverviewDto.getObjCode(),river.getRiverCode());
            mgtMsgEvolutionService.save(mgtMsgEvolution);
        //测验项目类型
        String proName = staOverviewDto.getStaSort();
        LambdaQueryWrapper<TestProMsg> testProMsgLambdaQueryWrapper = new LambdaQueryWrapper<>();
        testProMsgLambdaQueryWrapper.eq(TestProMsg::getProName,proName);
        TestProMsg testProMsg = testProMsgService.getOne(testProMsgLambdaQueryWrapper);
        //创建测验项目沿革
        TestProEvolution testProEvolution = new TestProEvolution(staOverviewDto.getStaCode(),testProMsg.getProCode());
        testProEvolutionService.save(testProEvolution);
        //测站地理位置
        StaLocation staLocation = new StaLocation(staOverviewDto.getObjCode(),staOverviewDto.getAddress());
        staLocationService.save(staLocation);
        //基本信息表增加
        StaBasicMsg staBasicMsg = new StaBasicMsg(staOverviewDto.getStaCode());
        basicMsgService.save(staBasicMsg);
        //测站一览表
        StaOverview staOverview = new StaOverview();
        BeanUtils.copyProperties(staOverviewDto,staOverview);
        staOverview.setObjName(staOverview.getStaName());
        save(staOverview);
        return R.successWithMsg("添加成功");
    }

    @Override
    @Transactional
    public R<String> modifySta(StaOverviewDto staOverviewDto) {
        //修改地址
        String address = staOverviewDto.getAddress();
        if (address!= null && !address.isEmpty()){
            LambdaUpdateWrapper<StaLocation> staLocationLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            staLocationLambdaUpdateWrapper.eq(StaLocation::getObjCode,staOverviewDto.getObjCode());
            staLocationLambdaUpdateWrapper.set(StaLocation::getAddress,address);
            staLocationService.update(staLocationLambdaUpdateWrapper);
        }
        //修改测站类型
        String staProName = staOverviewDto.getStaSort();
        LambdaQueryWrapper<TestProMsg> proMsgLambdaQueryWrapper = new LambdaQueryWrapper<>();
        proMsgLambdaQueryWrapper.eq(TestProMsg::getProName,staProName);
        TestProMsg testProMsg = testProMsgService.getOne(proMsgLambdaQueryWrapper);
        String proCode = testProMsg.getProCode();
        LambdaUpdateWrapper<TestProEvolution> testProEvolutionLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        testProEvolutionLambdaUpdateWrapper.eq(TestProEvolution::getStaCode,staOverviewDto.getStaCode());
        testProEvolutionLambdaUpdateWrapper.set(TestProEvolution::getProCode,proCode);
        testProEvolutionService.update(testProEvolutionLambdaUpdateWrapper);
        //修改测站名称
        String staName = staOverviewDto.getStaName();
        LambdaUpdateWrapper<StaOverview> overviewLambdaQueryWrapper = new LambdaUpdateWrapper<>();
        overviewLambdaQueryWrapper.eq(StaOverview::getObjCode,staOverviewDto.getObjCode());
        overviewLambdaQueryWrapper.eq(StaOverview::getStaCode,staOverviewDto.getStaCode());
        overviewLambdaQueryWrapper.set(StaOverview::getStaName,staName);
        this.update(overviewLambdaQueryWrapper);
        return R.successWithMsg("修改成功");
    }

    @Override
    public R<String> delete(String staCode) {
        LambdaQueryWrapper<StaOverview> overviewLambdaQueryWrapper = new LambdaQueryWrapper<>();
        overviewLambdaQueryWrapper.eq(StaOverview::getStaCode,staCode);
        StaOverview one = this.getOne(overviewLambdaQueryWrapper);
        String objCode = one.getObjCode();
        //一览表
        remove(overviewLambdaQueryWrapper);
        //基本信息
        LambdaUpdateWrapper<StaBasicMsg> baseLevelMsgLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        baseLevelMsgLambdaUpdateWrapper.eq(StaBasicMsg::getStaCode,staCode);
        basicMsgService.remove(baseLevelMsgLambdaUpdateWrapper);
        //地理位置
        LambdaUpdateWrapper<StaLocation> locationLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        locationLambdaUpdateWrapper.eq(StaLocation::getObjCode,objCode);
        staLocationService.remove(locationLambdaUpdateWrapper);
        //管理信息沿革表
        LambdaUpdateWrapper<StaMgtMsgEvolution> mgtMsgEvolutionLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        mgtMsgEvolutionLambdaUpdateWrapper.eq(StaMgtMsgEvolution::getObjCode,objCode);
        mgtMsgEvolutionService.remove(mgtMsgEvolutionLambdaUpdateWrapper);
        //测验项目表
        LambdaUpdateWrapper<TestProEvolution> testProEvolutionLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        testProEvolutionLambdaUpdateWrapper.eq(TestProEvolution::getStaCode,staCode);
        testProEvolutionService.remove(testProEvolutionLambdaUpdateWrapper);
        return R.successWithMsg("删除成功");
    }
}
