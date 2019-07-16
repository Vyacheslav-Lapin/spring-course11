package ru.sbt.courses.spring.springcourse10.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@lombok.Value
@Component("country")
public class CountryImpl implements Country {

  String name;

  public CountryImpl(
      @Value("${country.name}")
      String name) {
    this.name = name;
  }
}
