package ru.sbt.courses.spring.springcourse10;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Version;
import java.util.UUID;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NonNull;
import javax.persistence.Entity;
import javax.persistence.Id;

@SpringBootApplication
public class SpringCourse10Application {

  public static void main(String[] args) {
    SpringApplication.run(SpringCourse10Application.class, args);
  }

}

@RepositoryRestResource
interface CatRepository extends JpaRepository<Cat, UUID> {
}

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
class Cat {

  @Id
  @Exclude
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  UUID id;

  @Version
  int version;

  @NonNull
  String name;

}
