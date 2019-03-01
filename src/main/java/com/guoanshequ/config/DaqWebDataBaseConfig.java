package com.guoanshequ.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.config
 * @Description:daqWeb 数据
 * @Author: gbl
 * @CreateDate: 2018/11/1 11:12
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "daqwebEntityManagerFactory",//配置连接工厂 entityManagerFactory
         transactionManagerRef = "daqwebTransactionManager", //配置 事物管理器  transactionManager
         basePackages = {"com.guoanshequ.daqweb.repository"})//设置dao（repo）所在位置

public class DaqWebDataBaseConfig {

//        @Primary
        @Bean(name = "daqwebDataSource")
        @ConfigurationProperties(prefix = "spring.datasource.daqweb")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }

//        @Primary
        @Bean(name = "daqwebEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean daqwebEntityManagerFactory(
                EntityManagerFactoryBuilder builder, @Qualifier("daqwebDataSource") DataSource dataSource) {
            return builder.dataSource(dataSource).packages("com.guoanshequ.daqweb.domain").persistenceUnit("daqweb")
                    .build();
        }

//        @Primary
        @Bean(name = "daqwebTransactionManager")
        public PlatformTransactionManager daqwebTransactionManager(
                @Qualifier("daqwebEntityManagerFactory") EntityManagerFactory daqwebEntityManagerFactory) {
            return new JpaTransactionManager(daqwebEntityManagerFactory);
        }

}
