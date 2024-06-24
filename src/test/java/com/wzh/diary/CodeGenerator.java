package com.wzh.diary;

import com.baomidou.mybatisplus.generator.*;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;


import java.sql.Types;
import java.util.Collections;

/**
 * @author wzh
 * @date 2023年09月12日 15:14
 * Description:
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:dm://localhost:5236?schema=MY_USER";
        String username="MY_USER";
        String password = "MY_USER";
        String moduleName = "sys";
        String mapperLocation = "D:\\develop\\IDEACodes\\diary\\src\\main\\resources\\com\\wzh\\diary\\"+moduleName+"\\mapper";
        String tables = "D_ROLE,D_USER_ROLE";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("wzh") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式 // .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\develop\\IDEACodes\\diary\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wzh.diary") // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix("d_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
