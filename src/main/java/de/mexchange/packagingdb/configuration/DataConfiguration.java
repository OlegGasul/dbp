package de.mexchange.packagingdb.configuration;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Garik on 4/30/16.
 */
@Configuration
@EntityScan("de.mexchange.packagingdb.entity")
@EnableJpaRepositories("de.mexchange.packagingdb.repository")
@EnableTransactionManagement
public class DataConfiguration {

}