package com.wt.test.wolverine.infra.db.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.wt.test.wolverine.infra.db.component.BatchSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus 配置类
 *
 * @author qiyu
 * @date 2020/12/29
 */
@Configuration
@MapperScan("${mybatis-plus.basePackages:com.wt.test.wolverine.infra.db.**.dao}")
public class MybatisPlusConfig {
    
    @Value("${mybatis-plus.dbType:mysql}")
    private String dbType;
    
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.getDbType(dbType.toLowerCase())));
        return interceptor;
    }
    
    @Bean
    public ISqlInjector batchSqlInjector() {
        return new BatchSqlInjector();
    }
}
