package com.ciaosgarage.stickie.beans.contexts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
@Import({DaoContext.class, ServiceContext.class})
public class StickieContext {
    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost/stickie?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }
}
