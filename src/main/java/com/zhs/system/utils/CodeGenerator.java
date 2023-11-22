package com.zhs.system.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.zhs.system.config.BaseController;

import java.util.Collections;

public class CodeGenerator {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/wms?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/wms";
    private static final String DB_USERNAME = "root";

    private static final String DB_PASSWORD = "sa@123";

    //设置生成的目录名
    private static final String PACKAGE_NAME = "common";

    private static final String TABLE_NAME = "wms_loc_type";
    public static void main(String[] args) {
        FastAutoGenerator.create(DB_URL,DB_USERNAME,DB_PASSWORD)
                .globalConfig(builder -> {
                    builder.author("ZHU HANGSHUAI")
                            .disableOpenDir()
                            .outputDir("C://project//wms_backend//src//main//java//");
                })
                .packageConfig(builder -> {
                    builder.parent("com.zhs")
                            .moduleName(PACKAGE_NAME)
                            .pathInfo(Collections.singletonMap(OutputFile.xml,"C://project//wms_backend//src//main//resources//mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLE_NAME)

                            .entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .build()

                            .controllerBuilder()
                            .superClass(BaseController.class)
                            .enableRestStyle()
                            .build()

                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .build();
                })

                .execute();

    }
}
