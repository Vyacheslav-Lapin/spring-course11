package ru.sbt.courses.spring.springcourse10.ioc;

import java.time.LocalDate;
import lombok.Value;

@Value
public class EmployeeImpl implements Employee {

  String name;
  LocalDate dob;
  Company company;
  Country country;
}
