package com.scheduler.shedlock.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class SchedulerConfiguration {

    //If you need mysql
//    @Bean
//    public LockProvider lockProvider(DataSource dataSource) {
//        return new JdbcTemplateLockProvider(dataSource);
//
//    }
    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(
                JdbcTemplateLockProvider.Configuration.builder()
                        .withJdbcTemplate(new JdbcTemplate(dataSource))
                        .usingDbTime()
                        .build()
        );
    }

    //If you need h2
//    @Bean
//    public DataSource dataSource() {
//        return DataSourceBuilder
//                .create()
//                .url("jdbc:h2:mem:testdb")
//                .username("sa")
//                .password("password")
//                .driverClassName("org.h2.Driver")
//                .build();
//    }

}