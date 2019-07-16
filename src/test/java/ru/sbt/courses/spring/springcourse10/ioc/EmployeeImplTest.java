package ru.sbt.courses.spring.springcourse10.ioc;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@ComponentScan()
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:ioc.xml")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class EmployeeImplTest {

  Employee employee;

  @Test
  @DisplayName("getName TestConfig spring method works correctly")
  void getNameTestConfigspring() {
    assertThat(employee.getName())
      .isEqualTo("Вася Пупкин");
  }

  @Test
  @DisplayName("getName method works correctly")
  void getName() {
    val context = new ClassPathXmlApplicationContext("ioc.xml");
    assertThat(context.getBean("employee", Employee.class).getName())
        .isEqualTo("Вася Пупкин");
  }
}
