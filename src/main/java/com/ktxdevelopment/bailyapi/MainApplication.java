package com.ktxdevelopment.bailyapi;

import com.ktxdevelopment.bailyapi.security.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class, DataSourceAutoConfiguration.class})
@ComponentScan(value = "com.ktxdevelopment.bailyapi.io.repo")
public class MainApplication {

	public static void main(String[] args) { SpringApplication.run(MainApplication.class, args); }
}
