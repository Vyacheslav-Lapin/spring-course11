package ru.sbt.courses.spring.springcourse10.services;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class MyService {

  @SneakyThrows
  @Contract(pure = true)
  public @NotNull String method(int x) {
    return Integer.toString(x);
  }


}
