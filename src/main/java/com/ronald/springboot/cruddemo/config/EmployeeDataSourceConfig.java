package com.ronald.springboot.cruddemo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class EmployeeDataSourceConfig {

    // Injecting the properties using @Value annotation
    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.hikari.maximum-pool-size:10}")  // Default value is 10 if not present
    private int maxPoolSize;

    @Value("${spring.datasource.hikari.minimum-idle:5}")  // Default value is 5 if not present
    private int minIdle;

    @Value("${spring.datasource.hikari.idle-timeout:30000}")  // Default value is 30000 if not present
    private long idleTimeout;

    @Value("${spring.datasource.hikari.max-lifetime:1800000}")  // Default value is 1800000 if not present
    private long maxLifetime;

    @Value("${spring.datasource.hikari.connection-timeout:30000}")  // Default value is 30000 if not present
    private long connectionTimeout;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        hikariConfig.setMinimumIdle(minIdle);
        hikariConfig.setIdleTimeout(idleTimeout);
        hikariConfig.setMaxLifetime(maxLifetime);
        hikariConfig.setConnectionTimeout(connectionTimeout);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
