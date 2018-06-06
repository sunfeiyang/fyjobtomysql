package com.sunfy.fyjobtomysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//springBoot 中使用servlet 需要新加的注解---ServletComponentScan
@ServletComponentScan
public class FyjobtomysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(FyjobtomysqlApplication.class, args);
	}
}
