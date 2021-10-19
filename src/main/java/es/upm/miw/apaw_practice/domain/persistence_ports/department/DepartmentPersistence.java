package es.upm.miw.apaw_practice.domain.persistence_ports.department;

import es.upm.miw.apaw_practice.domain.models.department.Department;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface DepartmentPersistence {
    Stream<Department> readAll();
}
