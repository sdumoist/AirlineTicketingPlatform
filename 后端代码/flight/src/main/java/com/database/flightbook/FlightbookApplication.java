package com.database.flightbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.database.flightbook.mapper")
public class FlightbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightbookApplication.class, args);
    }

}
