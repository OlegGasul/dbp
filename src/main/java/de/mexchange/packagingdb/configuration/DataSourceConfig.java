package de.mexchange.packagingdb.configuration;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Hikari Connection Pool configuration.
 */
@SuppressWarnings("ALL")
@Configuration
public class DataSourceConfig {
    private static final String HIKARICP_CLOSE_METHOD = "close";

    @Bean(destroyMethod = HIKARICP_CLOSE_METHOD)
    DataSource dataSource(Environment env) {
        // initializing CP
        HikariConfig dataSourceConfig = new HikariConfig();

        // Applying configuration properties
        dataSourceConfig.setDriverClassName(
                env.getRequiredProperty("spring.datasource.driverClassName"));
        dataSourceConfig.setJdbcUrl(
                env.getRequiredProperty("spring.datasource.url"));
        dataSourceConfig.setUsername(
                env.getRequiredProperty("spring.datasource.username"));
        dataSourceConfig.setPassword(
                env.getRequiredProperty("spring.datasource.password"));
        dataSourceConfig.setMaximumPoolSize(
                Integer.parseInt(env.getRequiredProperty("spring.datasource.maximumPoolSize")));
        dataSourceConfig.setConnectionTestQuery(
                env.getRequiredProperty("spring.datasource.validation-query"));

        // returning instantiated bean
        return new HikariDataSource(dataSourceConfig);
    }

}