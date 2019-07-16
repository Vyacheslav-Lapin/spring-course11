package ru.sbt.courses.spring.springcourse10.ioc;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class EmployeeImplTest {

  @Test
  @DisplayName("GetName method works correctly")
  void getName() {
    val context = new ClassPathXmlApplicationContext("ioc.xml");
    assertThat(context.getBean("employee", Employee.class).getName())
        .isEqualTo("Вася Пупкин");
  }
}
