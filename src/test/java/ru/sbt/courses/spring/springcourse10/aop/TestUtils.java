package ru.sbt.courses.spring.springcourse10.aop;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import lombok.SneakyThrows;
import lombok.val;

public interface TestUtils {

  @SneakyThrows
  static String fromSystemOut(Runnable runnable) {

    PrintStream realOut = System.out;

    try (val out = new ByteArrayOutputStream();
         val printStream = new PrintStream(out)) {

      System.setOut(printStream);
      runnable.run();

      return new String(out.toByteArray()).intern();

    } finally {
      System.setOut(realOut);
    }
  }
}
