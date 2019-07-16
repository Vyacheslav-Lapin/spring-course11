package ru.sbt.courses.spring.springcourse10;

import static org.junit.Assert.assertEquals;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;

import com.jayway.jsonpath.JsonPath;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.sbt.courses.spring.springcourse10.ioc.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = "ADMIN")
public class SpringCourse10ApplicationTests {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  CatRepository catRepository;

  @Before
  public void setUp() {
    Stream.of("Murzik", "Barsik", "Matroskin", "")
        .map(Cat::new)
        .forEach(catRepository::save);
  }

  @Test
  @SneakyThrows
  public void contextLoads() {
    mockMvc.perform(MockMvcRequestBuilders.get("/cats"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(HAL_JSON_UTF8_VALUE))
        .andExpect(mvcResult -> assertEquals(4,
            JsonPath.parse(mvcResult.getResponse().getContentAsString())
                .<Integer>read("$.page.totalElements").intValue()));
  }

}
