package ru.sbt.courses.spring.springcourse10.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@lombok.Value
@Component("company")
public class CompanyImpl implements Company {

  String name;
  Country country;

  public CompanyImpl(
      @Value("${companyName}")
      String name,
      Country country) {
    this.name = name;
    this.country = country;
  }
}
