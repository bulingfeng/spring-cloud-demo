//package com.bulingfeng.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * @Author:bulingfeng
// * @Date: 2020-10-30
// */
//public class MybatisConfig2 {
//
//    @Configuration
//    @MapperScan(
//            basePackages = "com.bulingfeng.dao",
//            sqlSessionTemplateRef = "sqlSessionTemplate")
//    public static class Db1 {
//
//        @Bean
//        @Primary
//        public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
//            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//            factoryBean.setDataSource(dataSource);
//            return factoryBean.getObject();
//        }
//
//        @Bean
//        @Primary
//        public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//            return new SqlSessionTemplate(sqlSessionFactory);
//        }
//
//        @Bean("dataSourceTransactionManager")
////        @Primary
//        public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
//            return new DataSourceTransactionManager(dataSource);
//        }
//
//        @Bean("dataSourceTransactionManager2")
////        @Primary
//        public DataSourceTransactionManager dataSourceTransactionManager2(@Qualifier("masterDataSource") DataSource dataSource) {
//            return new DataSourceTransactionManager(dataSource);
//        }
//    }
//
////    @Configuration
////    @MapperScan(
////            basePackages = "com.bulingfeng.dao",
////            sqlSessionTemplateRef = "sqlSessionTemplate2")
////    public static class Db2 {
////
////        @Bean
////        public SqlSessionFactory sqlSessionFactory2(@Qualifier("slave1DataSource") DataSource dataSource) throws Exception {
////            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
////            factoryBean.setDataSource(dataSource);
////            return factoryBean.getObject();
////        }
////
////        @Bean
////        public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
////            return new SqlSessionTemplate(sqlSessionFactory);
////        }
////
////        @Bean
////        public DataSourceTransactionManager dataSourceTransactionManager2(@Qualifier("slave1DataSource") DataSource dataSource) {
////            return new DataSourceTransactionManager(dataSource);
////        }
////    }
//}
