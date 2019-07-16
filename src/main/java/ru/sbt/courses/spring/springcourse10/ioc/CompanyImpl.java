package ru.sbt.courses.spring.springcourse10.ioc;

import lombok.Value;

@Value
public class CompanyImpl implements Company {

  String name;
  Country country;
}
