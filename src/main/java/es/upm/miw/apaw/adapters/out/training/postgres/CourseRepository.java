package es.upm.miw.apaw.adapters.out.training.postgres;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface CourseRepository extends CrudRepository<CourseEntity, UUID> {
}
