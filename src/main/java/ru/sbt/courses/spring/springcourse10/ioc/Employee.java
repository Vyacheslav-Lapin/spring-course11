package ru.sbt.courses.spring.springcourse10.ioc;

public interface Employee {
  String getName();

  java.time.LocalDate getDob();

  Company getCompany();

  Country getCountry();
}
