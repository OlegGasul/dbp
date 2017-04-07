package de.mexchange.packagingdb.configuration;


import de.mexchange.packagingdb.liquebase.LastenheftLiquibase;
import liquibase.integration.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.boot.autoconfigure.liquibase.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

import javax.sql.*;

@Component
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(LiquibaseProperties.class)
public class SchemaAutoConfiguration {

    @Autowired
    private LiquibaseProperties properties;

    @Autowired
    private DataSource dataSource;

    private LastenheftLiquibase lastenheftLiquibase = new LastenheftLiquibase();

    @Bean
    public SpringLiquibase getSpringLiquibase() {
        return getLiquibase();
    }

    @Bean(name = "liquibase")
    @Primary
    public LastenheftLiquibase getLiquibase() {
        lastenheftLiquibase.setChangeLog(this.properties.getChangeLog());
        lastenheftLiquibase.setContexts(this.properties.getContexts());
        lastenheftLiquibase.setDataSource(getDataSource());
        lastenheftLiquibase.setDefaultSchema(this.properties.getDefaultSchema());
        lastenheftLiquibase.setDropFirst(false);
        lastenheftLiquibase.setShouldRun(true);
        return lastenheftLiquibase;
    }

    private DataSource getDataSource() {
        if (this.properties.getUrl() == null) {
            return this.dataSource;
        }
        return DataSourceBuilder.create().url(this.properties.getUrl())
                .username(this.properties.getUser())
                .password(this.properties.getPassword()).build();
    }


}
