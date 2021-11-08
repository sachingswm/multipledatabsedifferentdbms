package com.example.multipledatasource.configuration;

import com.example.multipledatasource.entity.b.B;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages =
        "com.example.multipledatasource.repository.b",
        entityManagerFactoryRef = "bEntityManagerFactory",
        transactionManagerRef = "bTransactionManager"
)
public class BDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.b")
    public DataSourceProperties bDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    @ConfigurationProperties("spring.datasource.b.configuration")
    public DataSource bDataSource() {
        return bDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "bEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String,Object> map=new HashMap<>();
        map.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        LocalContainerEntityManagerFactoryBean build = builder
                .dataSource(bDataSource())
                .packages(B.class)
                .build();
        build.setJpaPropertyMap(map);
        return build;
    }
    @Primary
    @Bean
    public PlatformTransactionManager bTransactionManager(
            final @Qualifier("bEntityManagerFactory") LocalContainerEntityManagerFactoryBean bEntityManagerFactory) {
        return new JpaTransactionManager(bEntityManagerFactory.getObject());
    }

}