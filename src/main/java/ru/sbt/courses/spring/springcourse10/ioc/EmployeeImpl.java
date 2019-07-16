package ru.sbt.courses.spring.springcourse10.ioc;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@lombok.Value
@Component("employee")
public final class EmployeeImpl implements Employee {

  String name;

  LocalDate dob;

  Company company;

  Country country;

  public EmployeeImpl(
      @Value("${employee.name}")
      String name,
      LocalDate dob,
      Company company,
      Country country) {
    this.name = name;
    this.dob = dob;
    this.company = company;
    this.country = country;
  }
}
