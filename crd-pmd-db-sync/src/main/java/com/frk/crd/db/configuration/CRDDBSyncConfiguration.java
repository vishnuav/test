package com.frk.crd.db.configuration;

import java.sql.SQLException;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.frk.crd.db.model"})
//@EnableJpaRepositories(basePackages = {"com.frk.crd.db.dao"})
public class CRDDBSyncConfiguration {
//  @Bean
//  public JdbcTemplate jdbcTemplate() throws SQLException {
//    OracleDataSource dataSource = new OracleDataSource();
//    dataSource.setDatabaseName("crd_owner");
//    dataSource.setURL("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=crimsdevdb-scan.corp.frk.com)(PORT=1521))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=CRDDEVC2)))");
//    dataSource.setUser("vayyala");
//    dataSource.setPassword("Weclome#321");
//    //    url: jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=crimsdevdb-scan.corp.frk.com)(PORT=1521))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=CRDDEVC2)))
//    //    driver-class-name: oracle.jdbc.OracleDriver
//    //    username: vayyala
//    //    password: Weclome#321
//    JdbcTemplate jdbcTemplate = new JdbcTemplate();
//    jdbcTemplate.setDataSource(dataSource);
//    return jdbcTemplate;
//  }
}