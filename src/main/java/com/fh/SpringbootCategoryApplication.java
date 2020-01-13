package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringbootCategoryApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootCategoryApplication.class, args);
  }

}
