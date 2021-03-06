package xyz.soulspace.cinder.api;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

public class Generator {
    public static void main(String[] args) {
        String property = System.getProperty("user.dir");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/CinderBotDB?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTime=Asia/shanghai&serverTimezone=UTC",
                        "root",
                        "MYSQLSoulSpace1114!")
                .globalConfig(builder -> {
                    builder.author("soulspace") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .outputDir(property + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("xyz.soulspace.cinder") // 设置父包名
                            .moduleName("api") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, property + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(//"ums_admin",
//                                      "ums_role_menu_relation",
                            "bms_comment"
//                                    "bms_blog"
//                                    "ums_permission",
//                                    "ums_menu",
//                                    "ums_resource",
//                                    "ums_role"
//                                    "ums_admin_login_log",
//                                    "ums_admin_permission_relation",
//                                    "ums_admin_role_relation",
//                                    "ums_growth_change_history",
//                                    "ums_integration_change_history",
//                                    "ums_integration_consume_setting"
                            ) // 设置需要生成的表名
                            .addTablePrefix("ums_", "t_", "bms_") // 设置过滤表前缀
                            .entityBuilder()
                            .disableSerialVersionUID()
                            .enableLombok()
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()
                            .enableActiveRecord()
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .serviceBuilder()
                            .superServiceClass(IService.class)
                            .superServiceImplClass(ServiceImpl.class)
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImp")
                            ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
