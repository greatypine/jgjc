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
 * @Description:gemini 数据
 * @Author: gbl
 * @CreateDate: 2018/11/1 16:00
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "geminiEntityManagerFactory",
        transactionManagerRef = "geminiTransactionManager",
        basePackages = {"com.guoanshequ.gemini.repository"})
public class GeminiDataBaseConfig {

    @Primary
    @Bean(name = "geminiDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.gemini")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "geminiEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean geminiEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("geminiDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.guoanshequ.gemini.domain").persistenceUnit("gemini")
                .build();
    }

    @Primary
    @Bean(name = "geminiTransactionManager")
    public PlatformTransactionManager geminiTransactionManager(
            @Qualifier("geminiEntityManagerFactory") EntityManagerFactory geminiEntityManagerFactory) {
        return new JpaTransactionManager(geminiEntityManagerFactory);
    }
}
