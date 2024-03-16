package com.tma.tma_warehouse_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@SpringBootApplication
public class TmaWarehouseServerApplication {


	public static void main(String[] args) {

		SpringApplication.run(TmaWarehouseServerApplication.class, args);
	}

}



