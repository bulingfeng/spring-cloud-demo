package com.bulingfeng.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-30
 */
@EnableTransactionManagement
@Configuration
public class MyBatisConfig {
    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;


    @Autowired
    @Qualifier("slave1DataSource")
    private DataSource slave1DataSource;


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(masterDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("platformTransactionManager")
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource);
    }

    @Bean("platformTransactionManager2")
    public PlatformTransactionManager platformTransactionManager2() {
        return new DataSourceTransactionManager(slave1DataSource);
    }
}
