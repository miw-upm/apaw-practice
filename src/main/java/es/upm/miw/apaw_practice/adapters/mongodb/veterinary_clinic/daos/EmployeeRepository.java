package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.EmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<EmployeeEntity, String> {
}