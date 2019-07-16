package ru.sbt.courses.spring.springcourse10;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.sbt.courses.spring.springcourse10.services.MyService;

@Slf4j
@SpringBootApplication
public class SpringCourse10Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SpringCourse10Application.class, args);

    System.out.println(context.getBean("myService", MyService.class).method(55));
    log.info(context.getBean("myService", MyService.class).method(55));
  }

}

