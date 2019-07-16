package ru.sbt.courses.spring.springcourse10;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.sbt.courses.spring.springcourse10.model.Cat;

@RepositoryRestResource
interface CatRepository extends JpaRepository<Cat, UUID> {
}
