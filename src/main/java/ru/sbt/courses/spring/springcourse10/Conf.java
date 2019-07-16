package ru.sbt.courses.spring.springcourse10;

import java.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:props.properties")
@ComponentScan("ru.sbt.courses.spring.springcourse10.ioc")
public class Conf {

  @Bean
  LocalDate dob() {
    return LocalDate.now();
  }
}
