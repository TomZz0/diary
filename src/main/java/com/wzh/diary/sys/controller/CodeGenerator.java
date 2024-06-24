package com.wzh.diary.sys.controller;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author wzh
 * @date 2023年09月12日 15:14
 * Description:
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hhu_database";
        String username="root";
        String password = "2536957";
        String mapperLocation = "D:\\develop\\IDEACodes\\hhu_database\\src\\main\\resources\\com\\r1105\\hhu_database\\"+"\\mapper";
        String tables = "base_level_msg,river_msg,sta_basic_msg,sta_location,sta_mgt_msg_evolution,sta_overview,test_pro_evolution,test_pro_msg,water_level,sanxia_depart";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("wzh") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            // .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\develop\\IDEACodes\\hhu_database\\src\\main\\java"); // 指定输出目录
                })

                .packageConfig(builder -> {
                    builder.parent("com.r1105.hhu_database") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables); // 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
