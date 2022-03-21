package io.gitee.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
@Configuration(proxyBeanMethods = false)
@MapperScan({"io.gitee.mybatis.mapper", "io.gitee.define.mapper"})
public class MapperAutoScannerConfiguration {
}
