package es.upm.miw.apaw.adapters.out.training.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    boolean existsByName(String name);
    List<CourseEntity> findAllByOrderByNameAsc();
}
