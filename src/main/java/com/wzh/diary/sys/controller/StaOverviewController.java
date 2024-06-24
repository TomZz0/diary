package com.wzh.diary.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzh.diary.sys.dto.StaOverviewDto;
import com.wzh.diary.sys.entity.*;
import com.wzh.diary.sys.service.IStaLocationService;
import com.wzh.diary.sys.service.IStaOverviewService;
import com.wzh.diary.sys.service.ITestProEvolutionService;
import com.wzh.diary.sys.service.ITestProMsgService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wzh
 * @since 2024-01-09
 */
@RestController
@RequestMapping("/staOverview")
public class StaOverviewController {

    @Autowired
    private IStaOverviewService staOverviewService;

    @Autowired
    private ITestProEvolutionService testProEvolutionService;

    @Autowired
    private IStaLocationService staLocationService;

    @Autowired
    private ITestProMsgService testProMsgService;

    @GetMapping("/page")
    public R<Page<StaOverviewDto>> getStaInfo(Integer pageNo, Integer pageSize,
          @RequestParam(value = "staName", required = false) String staName,
          @RequestParam(value = "staCode", required = false) String staCode,
          @RequestParam(value = "staSort", required = false) String staSort) {
        //创建分页构造器
        Page<StaOverview> pageInfo = new Page<>(pageNo, pageSize);
        Page<StaOverviewDto> dtoInfo = new Page<>();
        //设置查询条件
        LambdaQueryWrapper<StaOverview> staOverviewLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (staName != null) {
            staOverviewLambdaQueryWrapper.like(StaOverview::getStaName, staName);
        }
        if (staCode != null) {
            staOverviewLambdaQueryWrapper.eq(StaOverview::getStaCode, staCode);
        }
        String desProCode = null;
        String desProName = null;
        if (staSort != null && !staSort.isEmpty()) {
            List<String> staCodes = new ArrayList<>();
            //查目标站型
            LambdaQueryWrapper<TestProMsg> proWrapper = new LambdaQueryWrapper<>();
            proWrapper.like(TestProMsg::getProName, staSort);
            TestProMsg one = testProMsgService.getOne(proWrapper);
            //不存在目标站
            if (one == null) {
                return R.success(dtoInfo);
            }
            desProCode = one.getProCode();
            desProName = one.getProName();
            //查中间表得到staCodes 装填staCodes
            LambdaQueryWrapper<TestProEvolution> proEvolutionWrapper = new LambdaQueryWrapper<>();
            proEvolutionWrapper.eq(TestProEvolution::getProCode, desProCode);
            List<TestProEvolution> list = testProEvolutionService.list(proEvolutionWrapper);
            //判断是否为空
            if (list.isEmpty()) {
                return R.success(dtoInfo);
            }
            for (TestProEvolution testProEvolution : list) {
                staCodes.add(testProEvolution.getStaCode());
            }
            //执行查询操作
            staOverviewLambdaQueryWrapper.in(StaOverview::getStaCode, staCodes);
            staOverviewService.page(pageInfo, staOverviewLambdaQueryWrapper);
        } else {
            staOverviewService.page(pageInfo, staOverviewLambdaQueryWrapper);
        }

        //对象拷贝 因为要处理记录中类型 所以无需拷贝records
        BeanUtils.copyProperties(pageInfo, dtoInfo, "records");
        List<StaOverview> records = pageInfo.getRecords();
        List<StaOverviewDto> list = new LinkedList<>();
        for (StaOverview staOverview : records) {
            //将获取到的类别名赋给dto对象
            StaOverviewDto staOverviewDto = new StaOverviewDto();
            if (desProName == null) {
                //获取套餐的id 以便查询套餐名称
                String staCode1 = staOverview.getStaCode();
                //通过测站吗查询对象
                LambdaQueryWrapper<TestProEvolution> wrapper1 = new LambdaQueryWrapper<>();
                wrapper1.eq(TestProEvolution::getStaCode, staCode1);
                TestProEvolution testProEvolution = testProEvolutionService.getOne(wrapper1);
                //如果查询不到 就执行下一次循环
                if (testProEvolution == null) {
                    continue;
                }
                String proCode = testProEvolution.getProCode();
                //通过项目码查项目名字
                LambdaQueryWrapper<TestProMsg> wrapper2 = new LambdaQueryWrapper<>();
                wrapper2.eq(TestProMsg::getProCode, proCode);
                TestProMsg testProMsg = testProMsgService.getOne(wrapper2);
                staOverviewDto.setStaSort(testProMsg.getProName());
            } else {
                staOverviewDto.setStaSort(desProName);
            }
            //获取建站地址
            LambdaQueryWrapper<StaLocation>locationLambdaQueryWrapper = new LambdaQueryWrapper<>();
            locationLambdaQueryWrapper.eq(StaLocation::getObjCode,staOverview.getObjCode());
            StaLocation one = staLocationService.getOne(locationLambdaQueryWrapper);
            //设置建站地址
            staOverviewDto.setAddress(one.getAddress());
            //将其他staOverview信息拷贝到dto对象
            BeanUtils.copyProperties(staOverview, staOverviewDto);
            //添加到page中
            list.add(staOverviewDto);
        }
        dtoInfo.setRecords(list);
        return R.success(dtoInfo);
    }
    // @GetMapping("/page")
    // public R<Page<StaOverview>> getStaInfo(Integer pageNo, Integer pageSize,
    //                                        @RequestParam(value = "staName", required = false)String staName,
    //                                        @RequestParam(value = "staCode", required = false)String staCode,
    //                                        @RequestParam(value = "staType", required = false)String staType){
    //     //创建分页构造器
    //     Page<StaOverview> pageInfo = new Page<>(pageNo, pageSize);
    //     //设置查询条件
    //     LambdaQueryWrapper<StaOverview> staOverviewLambdaQueryWrapper = new LambdaQueryWrapper<>();
    //     if (staName != null){
    //         staOverviewLambdaQueryWrapper.like(StaOverview::getStaName,staName);
    //     }
    //     if (staCode!=null){
    //         staOverviewLambdaQueryWrapper.eq(StaOverview::getStaCode,staCode);
    //     }
    //
    //     //执行查询操作
    //     staOverviewService.page(pageInfo,staOverviewLambdaQueryWrapper);
    //     return R.success(pageInfo);
    // }
}
