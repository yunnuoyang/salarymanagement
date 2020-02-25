package com.joinx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.joinx.salary.repository")
public class SalaryApplication {
   public static void main(String[] args) {
      SpringApplication.run(SalaryApplication.class,args);
   }
   
}
